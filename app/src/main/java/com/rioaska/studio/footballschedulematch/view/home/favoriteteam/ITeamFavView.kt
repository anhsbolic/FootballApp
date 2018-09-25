package com.rioaska.studio.footballschedulematch.view.home.favoriteteam

import com.rioaska.studio.footballschedulematch.database.TeamDb


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface ITeamFavView {
    fun showTeamList(data: List<TeamDb>)
    fun showNoData(show: Boolean)
}