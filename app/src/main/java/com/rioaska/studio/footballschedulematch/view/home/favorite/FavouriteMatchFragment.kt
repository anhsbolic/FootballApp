package com.rioaska.studio.footballschedulematch.view.home.favorite

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
import com.rioaska.studio.footballschedulematch.R.id.ll_fav_container
import com.rioaska.studio.footballschedulematch.R.id.rvFav
import com.rioaska.studio.footballschedulematch.database.Favourite
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx

/**
 *
 * Created by Rio on 06/09/18.
 * Email rio.aska35@gmail.com
 *
 */
class FavouriteMatchFragment : Fragment(), AnkoComponent<Context>, IMatchFavView {

    private var match: MutableList<Favourite> = mutableListOf()
    private lateinit var presenter: FavMatchPresenter
    private lateinit var adapter: FavouriteMatchAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var tvNoData: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavouriteMatchAdapter(ctx, match)
        presenter = FavMatchPresenter(this@FavouriteMatchFragment, ctx)
        listTeam.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        match.clear()
        presenter.getMatch()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            id = ll_fav_container
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL

            relativeLayout {
                lparams(width = matchParent, height = wrapContent)

                listTeam = recyclerView {
                    id = rvFav
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

    override fun showMatchList(data: List<Favourite>) {
        match.addAll(data)
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

}