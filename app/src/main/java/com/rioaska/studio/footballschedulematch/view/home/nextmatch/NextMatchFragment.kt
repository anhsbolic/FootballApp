package com.rioaska.studio.footballschedulematch.view.home.nextmatch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.R.id.ll_next_container
import com.rioaska.studio.footballschedulematch.R.id.rvNext
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.database.League
import com.rioaska.studio.footballschedulematch.model.ItemSchedule
import com.rioaska.studio.footballschedulematch.view.detail.DetailActivity
import com.rioaska.studio.footballschedulematch.view.home.base.BaseFragment
import com.rioaska.studio.footballschedulematch.view.home.prevmatch.IMatchView
import com.rioaska.studio.footballschedulematch.view.settingleague.SpinnerAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast
import java.util.*


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class NextMatchFragment : BaseFragment(), IMatchView, AnkoComponent<Context> {

    private var match: MutableList<ItemSchedule> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: NextMatchAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var spinnerAdapter: ArrayAdapter<League>
    private val spinerItem = mutableListOf<League>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = NextMatchPresenter(this@NextMatchFragment, TheSportApi.instance, ctx)
        presenter.getLeague()

        adapter = NextMatchAdapter(match, { position ->
            goDetail(position)
        }, { position ->
            val item = match[position]
            goEvent(item)
        })

        listTeam.adapter = adapter
        swipeRefresh.onRefresh {
            presenter.getMatch(true, getIdLeague())
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                saveLeague(spinerItem[position])
                presenter.getMatch(false, spinerItem[position].idLeague ?: "")
            }
        }
    }

    override fun onStop() {
        presenter.onDestroy()
        super.onStop()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun showLoading(show: Boolean) {
        when (show) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.GONE
        }
    }

    override fun showMatchList(data: MutableList<ItemSchedule>) {
        swipeRefresh.isRefreshing = false
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            id = ll_next_container
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
                setColorSchemeResources(R.color.colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_bright)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = rvNext
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
        context?.startActivity<DetailActivity>("id" to match[position].idEvent,
                "eventTime" to "next",
                "home" to match[position].idHomeTeam,
                "away" to match[position].idAwayTeam,
                "homeTeam" to match[position].strHomeTeam,
                "awayTeam" to match[position].strAwayTeam,
                "date" to match[position].dateEvent,
                "league" to match[position].strLeague)
    }

    override fun showLeagueList(data: List<League>) {
        spinerItem.addAll(data)
        spinnerAdapter = SpinnerAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinerItem)
        spinner.adapter = spinnerAdapter
        spinnerAdapter.notifyDataSetChanged()
    }

    override fun showSpinner(show: Boolean) {
        when (show) {
            true -> spinner.visibility = View.VISIBLE
            false -> spinner.visibility = View.GONE
        }
    }

    private fun goEvent(item: ItemSchedule) {
        val date: List<String> = item.dateEvent!!.split("-")
        val intent = Intent(Intent.ACTION_INSERT)
        intent.type = "vnd.android.cursor.item/event"
        intent.putExtra(CalendarContract.Events.TITLE, "${item.strFilename}")
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "${item.strHomeTeam}")
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "${item.strFilename}")
        val calDate = GregorianCalendar(date[0].toInt(), date[1].replace("0", "").toInt() - 1, date[2].toInt())
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                calDate.timeInMillis)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                calDate.timeInMillis)
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
        intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE)
        intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
        startActivity(intent)
    }

}