package com.rioaska.studio.footballschedulematch.view.home.match

import com.rioaska.studio.footballschedulematch.database.League


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface IMatchTabView {
    fun showLeagueList(data: List<League>)
    fun showSpinner(show: Boolean)
}