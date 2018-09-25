package com.rioaska.studio.footballschedulematch.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Rio on 31/08/18.
 * Email rio.aska35@gmail.com
 *
 */
data class PlayerResponse(@SerializedName("player") var player: MutableList<Player>? = null)