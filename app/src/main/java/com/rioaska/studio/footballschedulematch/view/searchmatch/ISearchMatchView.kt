package com.rioaska.studio.footballschedulematch.view.searchmatch

import com.rioaska.studio.footballschedulematch.model.ItemSchedule


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface ISearchMatchView {
    fun showLoading(show: Boolean)
    fun showMessage(message: String)
    fun showMatchList(data: MutableList<ItemSchedule>)
}