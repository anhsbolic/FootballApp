package com.rioaska.studio.footballschedulematch.view.home.favoriteteam

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.R.id.ll_favteam_container
import com.rioaska.studio.footballschedulematch.R.id.rvFavTeam
import com.rioaska.studio.footballschedulematch.database.TeamDb
import com.rioaska.studio.footballschedulematch.view.detailteam.DetailTeamActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

/**
 *
 * Created by Rio on 06/09/18.
 * Email rio.aska35@gmail.com
 *
 */
class FavouriteTeamFragment : Fragment(), AnkoComponent<Context>, ITeamFavView {

    private var teams: MutableList<TeamDb> = mutableListOf()
    private lateinit var presenter: FavTeamPresenter
    private lateinit var adapter: FavouriteTeamAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var tvNoData: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavouriteTeamAdapter(ctx, teams) { position -> goDetail(position) }
        presenter = FavTeamPresenter(this@FavouriteTeamFragment, ctx)
        listTeam.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        teams.clear()
        presenter.getTeams()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            id = ll_favteam_container
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            relativeLayout {
                lparams(width = matchParent, height = wrapContent)

                listTeam = recyclerView {
                    id = rvFavTeam
                    lparams(width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }

                tvNoData = textView {
                    text = ctx.getString(R.string.no_data)
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    margin = dip(16)
                    centerInParent()
                }
            }
        }
    }

    override fun showTeamList(data: List<TeamDb>) {
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showNoData(show: Boolean) {
        when (show) {
            true -> {
                listTeam.visibility = GONE
                tvNoData.visibility = VISIBLE
            }
            false -> {
                listTeam.visibility = VISIBLE
                tvNoData.visibility = GONE
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


}