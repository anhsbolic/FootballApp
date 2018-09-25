package com.rioaska.studio.footballschedulematch.model

import com.google.gson.annotations.SerializedName

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
data class Player(
        @SerializedName("idPlayer") var idPlayer: String? = null,
        @SerializedName("idTeam") var idTeam: String? = null,
        @SerializedName("strPlayer") var strPlayer: String? = null,
        @SerializedName("strTeam") var strTeam: String? = null,
        @SerializedName("dateBorn") var dateBorn: String? = null,
        @SerializedName("dateSigned") var dateSigned: String? = null,
        @SerializedName("strBirthLocation") var strBirthLocation: String? = null,
        @SerializedName("strDescriptionEN") var strDescriptionEN: String? = null,
        @SerializedName("strGender") var strGender: String? = null,
        @SerializedName("strPosition") var strPosition: String? = null,
        @SerializedName("strThumb") var strThumb: String? = null,
        @SerializedName("strHeight") var strHeight: String? = null,
        @SerializedName("strWeight") var strWeight: String? = null,
        @SerializedName("strCutout") var strCutout: String? = null,
        @SerializedName("strFanart1") var strFanart1: String? = null,
        @SerializedName("strNationality") var strNationality: String? = null
)