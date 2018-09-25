package com.rioaska.studio.footballschedulematch.view.detail

import com.rioaska.studio.footballschedulematch.model.Match


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface IDetailMatchView {
    fun showLoading(show: Boolean)
    fun showBadgeImageHome(string: String)
    fun showBadgeImageAway(string: String)
    fun showMessage(message: String)
    fun showDetailMatch(data: Match)
    fun showErrorLayout(show: Boolean)
}