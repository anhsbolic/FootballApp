package com.rioaska.studio.footballschedulematch.view.home.favoriteteam

import android.content.Context
import com.rioaska.studio.footballschedulematch.database.TeamDb
import com.rioaska.studio.footballschedulematch.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class FavTeamPresenter(private val view: ITeamFavView, private val context: Context) {

    fun getTeams() {
        context.database.use {
            val result = select(TeamDb.TABLE_TEAM)
            val teams = result.parseList(classParser<TeamDb>())
            view.showTeamList(teams)
            if (teams.isEmpty()) {
                view.showNoData(true)
            } else {
                view.showNoData(false)
            }
        }
    }
}