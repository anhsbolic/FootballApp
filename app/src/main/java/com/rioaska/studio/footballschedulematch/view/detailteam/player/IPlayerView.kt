package com.rioaska.studio.footballschedulematch.view.detailteam.player

import com.rioaska.studio.footballschedulematch.model.Player


/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
interface IPlayerView {
    fun showLoading(show: Boolean)
    fun showMessage(message: String)
    fun showPlayerList(data: MutableList<Player>)
}