package com.rioaska.studio.footballschedulematch.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
data class LeagueResponse(@SerializedName("countrys") var leagues: MutableList<League>? = null)