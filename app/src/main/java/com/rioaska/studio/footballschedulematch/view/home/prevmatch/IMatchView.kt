package com.rioaska.studio.footballschedulematch.view.home.prevmatch

import com.rioaska.studio.footballschedulematch.database.League
import com.rioaska.studio.footballschedulematch.model.ItemSchedule


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface IMatchView {
    fun showLoading(show: Boolean)
    fun showMessage(message: String)
    fun showMatchList(data: MutableList<ItemSchedule>)
    fun showLeagueList(data: List<League>)
    fun showSpinner(show: Boolean)
}