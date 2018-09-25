package com.rioaska.studio.footballschedulematch.view.detail

import android.R.id.home
import android.annotation.SuppressLint
import android.app.Activity
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.R.drawable.v_ic_add_to_favorites
import com.rioaska.studio.footballschedulematch.R.drawable.v_ic_added_to_favorites
import com.rioaska.studio.footballschedulematch.R.id.add_to_favorite
import com.rioaska.studio.footballschedulematch.R.menu.menu_detail_event
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.database.Favourite
import com.rioaska.studio.footballschedulematch.database.database
import com.rioaska.studio.footballschedulematch.ext.changeLineDetailMatch
import com.rioaska.studio.footballschedulematch.ext.formatDate
import com.rioaska.studio.footballschedulematch.ext.loadImage
import com.rioaska.studio.footballschedulematch.model.Match
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class DetailActivity : AppCompatActivity(), IDetailMatchView {

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var match: Match
    private var idEvent: String = ""
    private var event: String = ""
    private var menuItem: Menu? = null
    private var isFavourite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
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
                if (isFavourite) removeFromFavourite() else addToFavourite()
                isFavourite = !isFavourite
                setFavourite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun init() {
        setSupportActionBar(toolbar_match_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        idEvent = intent.getStringExtra("id")
        event = intent.getStringExtra("eventTime")
        presenter = DetailMatchPresenter(this, TheSportApi.instance)
        presenter.getBadge(intent.getStringExtra("home"), 1)
        presenter.getBadge(intent.getStringExtra("away"), 2)
        favoriteState()

        when (event) {
            "past" -> setupEventPastMatch()
            "next" -> setupEventNextMatch()
            "local" -> setupEventFromDbMatch()
        }

        btn_coba_lagi.setOnClickListener {
            getData()
        }

    }

    private fun getData() {
        ll_error.visibility = GONE
        presenter.getDetailMatch(idEvent)
    }

    override fun showLoading(show: Boolean) {
        when (show) {
            true -> {
                pb_match_detail.visibility = VISIBLE
                content_match_detail.visibility = GONE
            }
            false -> {
                pb_match_detail.visibility = GONE
                content_match_detail.visibility = VISIBLE
            }
        }
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    @SuppressLint("SetTextI18n")
    override fun showDetailMatch(data: Match) {
        match = data
        tv_home_team_detail.text = data.strHomeTeam
        tv_away_team_detail.text = data.strAwayTeam
        tv_skor_detail.text = "${data.intHomeScore} - ${data.intAwayScore}"
        tv_date_detail.text = formatDate(data.dateEvent ?: "")
        tv_league_detail.text = data.strLeague
        tv_goal_home_team.text = data.strHomeGoalDetails?.changeLineDetailMatch()
        tv_goal_away_team.text = data.strAwayGoalDetails?.changeLineDetailMatch()

        tv_gk_home_team.text = data.strHomeLineupGoalkeeper?.changeLineDetailMatch()
        tv_gk_away_team.text = data.strAwayLineupGoalkeeper?.changeLineDetailMatch()

        tv_df_home_team.text = data.strHomeLineupDefense?.changeLineDetailMatch()
        tv_df_away_team.text = data.strAwayLineupDefense?.changeLineDetailMatch()

        tv_mdf_home_team.text = data.strHomeLineupMidfield?.changeLineDetailMatch()
        tv_mdf_away_team.text = data.strAwayLineupMidfield?.changeLineDetailMatch()

        tv_fw_home_team.text = data.strHomeLineupForward?.changeLineDetailMatch()
        tv_fw_away_team.text = data.strAwayLineupForward?.changeLineDetailMatch()
    }

    override fun showErrorLayout(show: Boolean) {
        when (show) {
            true -> {
                ll_error.visibility = VISIBLE
                content_match_detail.visibility = GONE
            }
            false -> {
                ll_error.visibility = GONE
                content_match_detail.visibility = VISIBLE
            }
        }
    }

    private fun setupEventPastMatch() {
        getData()
        ll_highlight.visibility = VISIBLE
    }

    private fun setupEventNextMatch() {
        tv_skor_detail.text = getString(R.string.versus)
        tv_highlight.text = getString(R.string.highlight_coming_soon)
        tv_home_team_detail.text = intent.getStringExtra("homeTeam")
        tv_away_team_detail.text = intent.getStringExtra("awayTeam")
        val date = intent.getStringExtra("date")
        tv_date_detail.text = formatDate(date)
        tv_league_detail.text = intent.getStringExtra("league")
        ll_highlight.visibility = GONE
    }

    private fun setupEventFromDbMatch() {
        database.use {
            val result = select(Favourite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to idEvent)
            val favoriteList = result.parseList(classParser<Favourite>())
            val fav = favoriteList[0]
            setupLocalDisplay(fav)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favourite.TABLE_FAVORITE)
                    .whereArgs("(EVENT_ID = {id})",
                            "id" to idEvent)
            val favorite = result.parseList(classParser<Favourite>())
            if (!favorite.isEmpty()) {
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

    private fun removeFromFavourite() {
        try {
            database.use {
                delete(Favourite.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to idEvent)
            }
            snackbar(cl_match_detail, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(cl_match_detail, e.localizedMessage).show()
        }
    }

    private fun addToFavourite() {
        try {
            database.use {
                if (event == "past") {
                    insert(Favourite.TABLE_FAVORITE,
                            Favourite.EVENT_ID to idEvent,
                            Favourite.EVENT_DATE to match.dateEvent,
                            Favourite.HOME_ID to match.idHomeTeam,
                            Favourite.HOME_NAME to match.strHomeTeam,
                            Favourite.HOME_SCORE to match.intHomeScore,
                            Favourite.HOME_GOAL to match.strHomeGoalDetails,
                            Favourite.HOME_LINEUP_GK to match.strHomeLineupGoalkeeper,
                            Favourite.HOME_LINEUP_DEF to match.strHomeLineupDefense,
                            Favourite.HOME_LINEUP_MID to match.strHomeLineupMidfield,
                            Favourite.HOME_LINEUP_FW to match.strHomeLineupForward,
                            Favourite.AWAY_ID to match.idAwayTeam,
                            Favourite.AWAY_NAME to match.strAwayTeam,
                            Favourite.AWAY_SCORE to match.intAwayScore,
                            Favourite.AWAY_GOAL to match.strAwayGoalDetails,
                            Favourite.AWAY_LINEUP_GK to match.strAwayLineupGoalkeeper,
                            Favourite.AWAY_LINEUP_DEF to match.strAwayLineupDefense,
                            Favourite.AWAY_LINEUP_MID to match.strAwayLineupMidfield,
                            Favourite.AWAY_LINEUP_FW to match.strAwayLineupForward,
                            Favourite.EVENT_MATCH to event,
                            Favourite.LEAGUE_NAME to match.strLeague)
                } else {
                    insert(Favourite.TABLE_FAVORITE,
                            Favourite.EVENT_ID to idEvent,
                            Favourite.EVENT_DATE to intent.getStringExtra("date"),
                            Favourite.HOME_ID to intent.getStringExtra("home"),
                            Favourite.HOME_NAME to intent.getStringExtra("homeTeam"),
                            Favourite.AWAY_ID to intent.getStringExtra("away"),
                            Favourite.AWAY_NAME to intent.getStringExtra("awayTeam"),
                            Favourite.EVENT_MATCH to event,
                            Favourite.LEAGUE_NAME to intent.getStringExtra("league"))
                }
            }
            snackbar(cl_match_detail, "Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(cl_match_detail, e.localizedMessage).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupLocalDisplay(fav: Favourite) {
        when (fav.eventMatch) {
            "next" -> {
                tv_skor_detail.text = getString(R.string.versus)
                tv_highlight.text = getString(R.string.highlight_coming_soon)
                tv_home_team_detail.text = fav.homeName
                tv_away_team_detail.text = fav.awayName
                tv_date_detail.text = formatDate(fav.eventDate ?: "")
                tv_league_detail.text = fav.leagueName
                ll_highlight.visibility = GONE
            }
            "past" -> {
                tv_home_team_detail.text = fav.homeName
                tv_away_team_detail.text = fav.awayName
                tv_skor_detail.text = "${fav.homeScore} - ${fav.awayScore}"
                tv_date_detail.text = formatDate(fav.eventDate ?: "")
                tv_league_detail.text = fav.leagueName
                tv_goal_home_team.text = fav.homeGoal?.replace(";", "")
                tv_goal_away_team.text = fav.awayGoal?.replace(";", "")

                tv_gk_home_team.text = fav.homeLineupGk?.replace(";", "")
                tv_gk_away_team.text = fav.awayLineupGk?.replace(";", "")

                tv_df_home_team.text = fav.homeLineupDef?.replace(";", "")
                tv_df_away_team.text = fav.homeLineupDef?.replace(";", "")

                tv_mdf_home_team.text = fav.homeLineupMid?.replace(";", "")
                tv_mdf_away_team.text = fav.homeLineupMid?.replace(";", "")

                tv_fw_home_team.text = fav.homeLineupFW?.replace(";", "")
                tv_fw_away_team.text = fav.homeLineupFW?.replace(";", "")
            }
        }
    }

    override fun showBadgeImageHome(string: String) {
        loadImage(this, string, iv_badge_home)
    }

    override fun showBadgeImageAway(string: String) {
        loadImage(this, string, iv_badge_away)
    }

}
