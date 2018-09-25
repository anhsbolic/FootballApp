package com.rioaska.studio.footballschedulematch.view.searchmatch

import android.R.id.home
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.ext.checkBeforeDate
import com.rioaska.studio.footballschedulematch.model.ItemSchedule
import com.rioaska.studio.footballschedulematch.view.detail.DetailActivity
import com.rioaska.studio.footballschedulematch.view.home.base.BaseActivity
import com.rioaska.studio.footballschedulematch.view.home.prevmatch.PrevMatchAdapter
import kotlinx.android.synthetic.main.activity_search_main.*
import kotlinx.android.synthetic.main.content_search.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *
 * Created by Rio on 21/09/18.
 * Email rio.aska35@gmail.com
 *
 */
class SearchMatchActivity : BaseActivity(), ISearchMatchView {

    private var matchs: MutableList<ItemSchedule> = mutableListOf()
    private lateinit var presenter: SearchMatchListPresenter
    private lateinit var adapter: PrevMatchAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_main)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_match, menu)

        val menuSearch = menu.findItem(R.id.search_match)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (menuSearch != null) {
            searchView = menuSearch.actionView as SearchView
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))
        searchView.isIconified = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    presenter.getSearchMatch(newText ?: "")
                }
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean = true

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when (id) {
            home -> {
                back()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading(show: Boolean) {
        when (show) {
            true -> pb_load_search.visibility = View.VISIBLE
            false -> pb_load_search.visibility = View.GONE
        }
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showMatchList(data: MutableList<ItemSchedule>) {
        matchs.clear()
        matchs.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun init() {
        setSupportActionBar(toolbar_search as Toolbar?)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = SearchMatchListPresenter(this, TheSportApi.instance)
        rv_list_search.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        rv_list_search.layoutManager = layoutManager
        adapter = PrevMatchAdapter(matchs) { position ->
            goDetail(position)
        }
        rv_list_search.adapter = adapter
    }

    private fun goDetail(position: Int) {
        val match = matchs[position]
        if (!match.idHomeTeam.isNullOrEmpty() || !match.idAwayTeam.isNullOrEmpty()) {
            if (checkBeforeDate(match.dateEvent ?: "")) {
                startActivity<DetailActivity>("id" to match.idEvent,
                        "eventTime" to "next",
                        "home" to match.idHomeTeam,
                        "away" to match.idAwayTeam,
                        "homeTeam" to match.strHomeTeam,
                        "awayTeam" to match.strAwayTeam,
                        "date" to match.dateEvent,
                        "league" to match.strLeague)
            } else {
                startActivity<DetailActivity>("id" to match.idEvent,
                        "eventTime" to "past",
                        "home" to match.idHomeTeam,
                        "away" to match.idAwayTeam)
            }
        }
    }

}