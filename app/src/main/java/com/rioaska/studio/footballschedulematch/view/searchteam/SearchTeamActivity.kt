package com.rioaska.studio.footballschedulematch.view.searchteam

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
import com.rioaska.studio.footballschedulematch.database.TeamDb
import com.rioaska.studio.footballschedulematch.model.Team
import com.rioaska.studio.footballschedulematch.view.detailteam.DetailTeamActivity
import com.rioaska.studio.footballschedulematch.view.home.base.BaseActivity
import com.rioaska.studio.footballschedulematch.view.home.teams.TeamListAdapter
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
class SearchTeamActivity : BaseActivity(), ISearchView {

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: SearchTeamListPresenter
    private lateinit var adapter: TeamListAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_main)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search_team, menu)

        val menuSearch = menu.findItem(R.id.search_team)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if (menuSearch != null) {
            searchView = menuSearch.actionView as SearchView
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))
        searchView.isIconified = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    presenter.getSearchTeams(newText ?: "")
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

    override fun showTeamList(data: MutableList<Team>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun init() {
        setSupportActionBar(toolbar_search as Toolbar?)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = SearchTeamListPresenter(this, TheSportApi.instance)
        rv_list_search.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        rv_list_search.layoutManager = layoutManager
        adapter = TeamListAdapter(this, teams) { position ->
            goDetail(position)
        }
        rv_list_search.adapter = adapter
    }

    private fun goDetail(position: Int) {
        startActivity<DetailTeamActivity>(TeamDb.TEAM_ID to teams[position].idTeam,
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