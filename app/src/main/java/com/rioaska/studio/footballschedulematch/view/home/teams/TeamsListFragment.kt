package com.rioaska.studio.footballschedulematch.view.home.teams

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.R.color.colorAccent
import com.rioaska.studio.footballschedulematch.R.id.rvTeams
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.database.League
import com.rioaska.studio.footballschedulematch.database.TeamDb
import com.rioaska.studio.footballschedulematch.model.Team
import com.rioaska.studio.footballschedulematch.view.detailteam.DetailTeamActivity
import com.rioaska.studio.footballschedulematch.view.home.base.BaseFragment
import com.rioaska.studio.footballschedulematch.view.settingleague.SpinnerAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class TeamsListFragment : BaseFragment(), ITeamsView, AnkoComponent<Context> {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamListPresenter
    private lateinit var adapter: TeamListAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var spinnerAdapter: ArrayAdapter<League>
    private val spinerItem = mutableListOf<League>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = TeamListPresenter(this@TeamsListFragment, TheSportApi.instance, ctx)
        presenter.getLeague()

        adapter = TeamListAdapter(ctx, teams) { position ->
            goDetail(position)
        }

        listTeam.adapter = adapter
        swipeRefresh.onRefresh {
            presenter.getTeams(true, getIdLeague())
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                saveLeague(spinerItem[position])
                presenter.getTeams(false, spinerItem[position].idLeague ?: "")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun showLoading(show: Boolean) {
        when (show) {
            true -> progressBar.visibility = VISIBLE
            false -> progressBar.visibility = GONE
        }
    }

    override fun onStop() {
        presenter.onDestroy()
        super.onStop()
    }

    override fun showTeamList(data: MutableList<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)

        val menuSearch = menu?.findItem(R.id.action_search_team)
        menuSearch?.isVisible = true
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            id = R.id.llTeamList
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            frameLayout {
                lparams(matchParent, wrapContent)
                backgroundDrawable = ctx.getDrawable(R.drawable.rounded_white_button_medsos)

                spinner = spinner {
                    id = R.id.spPrev
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }
            }.lparams {
                width = matchParent
                height = wrapContent
                margin = dip(5)
            }

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_bright)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = rvTeams
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerInParent()
                    }
                }
            }
        }
    }

    private fun goDetail(position: Int) {
        context?.startActivity<DetailTeamActivity>(TeamDb.TEAM_ID to teams[position].idTeam,
                TeamDb.STR_TEAM to teams[position].strTeam,
                TeamDb.FORMED_YEAR to teams[position].intFormedYear,
                TeamDb.STR_STADIUM to teams[position].strStadium,
                TeamDb.STR_WEBSITE to teams[position].strWebsite,
                TeamDb.STR_DESC_EN to teams[position].strDescriptionEN,
                TeamDb.STR_TEAM_BADGE to teams[position].strTeamBadge,
                TeamDb.STR_TEAM_JERSEY to teams[position].strTeamJersey,
                TeamDb.STR_TEAM_FANART1 to teams[position].strTeamFanart1)
    }

    override fun showLeagueList(data: List<League>) {
        spinerItem.addAll(data)
        spinnerAdapter = SpinnerAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinerItem)
        spinner.adapter = spinnerAdapter
        spinnerAdapter.notifyDataSetChanged()
    }

    override fun showSpinner(show: Boolean) {
        when (show) {
            true -> spinner.visibility = VISIBLE
            false -> spinner.visibility = GONE
        }
    }
}