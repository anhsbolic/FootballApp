package com.rioaska.studio.footballschedulematch.view.searchteam

import com.rioaska.studio.footballschedulematch.model.Team


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface ISearchView {
    fun showLoading(show: Boolean)
    fun showMessage(message: String)
    fun showTeamList(data: MutableList<Team>)
}