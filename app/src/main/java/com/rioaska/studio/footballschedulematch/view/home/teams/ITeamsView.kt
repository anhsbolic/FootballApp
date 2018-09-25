package com.rioaska.studio.footballschedulematch.view.home.teams

import com.rioaska.studio.footballschedulematch.model.Team


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface ITeamsView {
    fun showLoading(show: Boolean)
    fun showMessage(message: String)
    fun showTeamList(data: MutableList<Team>)
    fun showLeagueList(data: List<com.rioaska.studio.footballschedulematch.database.League>)
    fun showSpinner(show: Boolean)
}