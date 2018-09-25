package com.rioaska.studio.footballschedulematch.view.detailteam.player

import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.rioaska.studio.footballschedulematch.R.color.colorAccent
import com.rioaska.studio.footballschedulematch.R.id.rvPrev
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.database.TeamDb
import com.rioaska.studio.footballschedulematch.model.Player
import com.rioaska.studio.footballschedulematch.util.Cons
import com.rioaska.studio.footballschedulematch.view.detailplayer.DetailPlayerActivity
import com.rioaska.studio.footballschedulematch.view.home.base.BaseFragment
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
class PlayersListFragment : BaseFragment(), IPlayerView, AnkoComponent<Context> {

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerListPresenter
    private lateinit var adapter: PlayerListAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = PlayerListPresenter(this@PlayersListFragment, TheSportApi.instance)
        presenter.getPlayers(false, activity?.intent?.getStringExtra(TeamDb.TEAM_ID) ?: "")

        adapter = PlayerListAdapter(ctx, players) { position ->
            goDetail(position)
        }

        listTeam.adapter = adapter
        swipeRefresh.onRefresh {
            presenter.getPlayers(true, activity?.intent?.getStringExtra(TeamDb.TEAM_ID) ?: "")
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

    override fun showPlayerList(data: MutableList<Player>) {
        swipeRefresh.isRefreshing = false
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_blue_bright)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = rvPrev
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
        val player = players[position]
        context?.startActivity<DetailPlayerActivity>(Cons.NAME_PLAYER to player.strPlayer,
                Cons.COVER_PLAYER to player.strFanart1,
                Cons.BADGE_PLAYER to player.strCutout,
                Cons.POSITION_PLAYER to player.strPosition,
                Cons.DESC_PLAYER to player.strDescriptionEN,
                Cons.HEIGHT_PLAYER to player.strHeight,
                Cons.WEIGHT_PLAYER to player.strWeight)
    }
}