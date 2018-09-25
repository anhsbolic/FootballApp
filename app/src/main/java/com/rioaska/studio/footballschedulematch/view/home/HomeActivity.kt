package com.rioaska.studio.footballschedulematch.view.home

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.R.id.*
import com.rioaska.studio.footballschedulematch.view.home.favoritetab.FavoriteTabFragment
import com.rioaska.studio.footballschedulematch.view.home.match.MatchTabFragment
import com.rioaska.studio.footballschedulematch.view.home.teams.TeamsListFragment
import com.rioaska.studio.footballschedulematch.view.searchmatch.SearchMatchActivity
import com.rioaska.studio.footballschedulematch.view.searchteam.SearchTeamActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {

    private var doubleTapExit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nav_bottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                action_b_prev -> loadMatchFragment(savedInstanceState)
                action_b_next -> loadTeamsFragment(savedInstanceState)
                action_b_fav -> loadFavMatchFragment(savedInstanceState)
            }
            true
        }
        nav_bottom.selectedItemId = action_b_prev
    }

    override fun onBackPressed() {
        if (doubleTapExit) {
            super.onBackPressed()
            return
        }

        this.doubleTapExit = true
        toast(getString(R.string.msg_exit))
        Handler().postDelayed({ doubleTapExit = false }, 2000)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when (id) {
            R.id.action_search_match -> {
                startActivity<SearchMatchActivity>()
            }
            R.id.action_search_team -> {
                startActivity<SearchTeamActivity>()
            }

        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_container_home, MatchTabFragment(), MatchTabFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_container_home, TeamsListFragment(), TeamsListFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_container_home, FavoriteTabFragment(), FavoriteTabFragment::class.java.simpleName)
                    .commit()
        }
    }

}
