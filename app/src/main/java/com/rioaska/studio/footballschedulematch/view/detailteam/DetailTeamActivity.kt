package com.rioaska.studio.footballschedulematch.view.detailteam

import android.R.id.home
import android.app.Activity
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.R.drawable.v_ic_add_to_favorites
import com.rioaska.studio.footballschedulematch.R.drawable.v_ic_added_to_favorites
import com.rioaska.studio.footballschedulematch.R.id.add_to_favorite
import com.rioaska.studio.footballschedulematch.R.menu.menu_detail_event
import com.rioaska.studio.footballschedulematch.database.TeamDb
import com.rioaska.studio.footballschedulematch.database.database
import com.rioaska.studio.footballschedulematch.ext.loadImage
import com.rioaska.studio.footballschedulematch.view.home.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class DetailTeamActivity : BaseActivity() {

    private var idTeam: String = ""
    private val titles = arrayOf<CharSequence>("Players", "Overview")
    private val numbOfTabs = 2
    private var menuItem: Menu? = null
    private var isFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_detail_event, menu)
        menuItem = menu
        setFavourite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            home -> {
                setResult(Activity.RESULT_OK)
                finish()
                true
            }
            add_to_favorite -> {
                if (isFavourite) removeFromLocal() else addToFavourite()
                isFavourite = !isFavourite
                setFavourite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init() {
        setSupportActionBar(toolbar_team_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        idTeam = intent.getStringExtra(TeamDb.TEAM_ID)
        tv_str_team.text = intent.getStringExtra(TeamDb.STR_TEAM)
        tv_str_stadium.text = intent.getStringExtra(TeamDb.STR_STADIUM)
        localTeamState()
        val adapter = ViewPagerDetailTeamAdapter(supportFragmentManager, titles, numbOfTabs)
        vp_team_detail.adapter = adapter
        tab_team_detail.setupWithViewPager(vp_team_detail)

        if (!isFinishing) {
            if (!intent.getStringExtra(TeamDb.STR_TEAM_BADGE).isNullOrEmpty()) {
                loadImage(this, intent.getStringExtra(TeamDb.STR_TEAM_BADGE), iv_badge_detail)
            }
        }
    }

    private fun localTeamState() {
        database.use {
            val result = select(TeamDb.TABLE_TEAM)
                    .whereArgs("(TEAM_ID = {id})",
                            "id" to idTeam)
            val team = result.parseList(classParser<TeamDb>())
            if (!team.isEmpty()) {
                isFavourite = true
            }
        }
    }

    private fun setFavourite() {
        if (isFavourite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, v_ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, v_ic_add_to_favorites)
    }

    private fun removeFromLocal() {
        try {
            database.use {
                delete(TeamDb.TABLE_TEAM, "(TEAM_ID = {id})",
                        "id" to idTeam)
            }
            toast("Removed to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }

    private fun addToFavourite() {
        try {
            database.use {
                insert(TeamDb.TABLE_TEAM,
                        TeamDb.TEAM_ID to idTeam,
                        TeamDb.STR_TEAM to intent.getStringExtra(TeamDb.STR_TEAM),
                        TeamDb.FORMED_YEAR to intent.getStringExtra(TeamDb.FORMED_YEAR),
                        TeamDb.STR_STADIUM to intent.getStringExtra(TeamDb.STR_STADIUM),
                        TeamDb.STR_WEBSITE to intent.getStringExtra(TeamDb.STR_WEBSITE),
                        TeamDb.STR_DESC_EN to intent.getStringExtra(TeamDb.STR_DESC_EN),
                        TeamDb.STR_TEAM_BADGE to intent.getStringExtra(TeamDb.STR_TEAM_BADGE),
                        TeamDb.STR_TEAM_JERSEY to intent.getStringExtra(TeamDb.STR_TEAM_JERSEY),
                        TeamDb.STR_TEAM_FANART1 to intent.getStringExtra(TeamDb.STR_TEAM_FANART1))
            }
            toast("Added to favorite")
        } catch (e: SQLiteConstraintException) {
            toast(e.localizedMessage)
        }
    }


}
