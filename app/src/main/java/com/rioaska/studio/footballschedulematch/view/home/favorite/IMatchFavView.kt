package com.rioaska.studio.footballschedulematch.view.home.favorite

import com.rioaska.studio.footballschedulematch.database.Favourite


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface IMatchFavView {
    fun showMatchList(data: List<Favourite>)
    fun showNoData(show: Boolean)
}