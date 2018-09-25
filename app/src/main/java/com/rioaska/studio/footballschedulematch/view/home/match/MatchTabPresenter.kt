package com.rioaska.studio.footballschedulematch.view.home.match

import android.content.Context
import com.rioaska.studio.footballschedulematch.database.League
import com.rioaska.studio.footballschedulematch.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class MatchTabPresenter(private val view: IMatchTabView, private val context: Context) {

    fun getLeague() {
        context.database.use {
            val result = select(League.TABLE_LEAGUE)
            val league = result.parseList(classParser<League>())
            view.showLeagueList(league)
            if (league.isEmpty()) {
                view.showSpinner(false)
            } else {
                view.showSpinner(true)
            }
        }
    }
}