package com.rioaska.studio.footballschedulematch.database

/**
 *
 * Created by Rio on 06/09/18.
 * Email rio.aska35@gmail.com
 *
 */
data class League(val id: Long?, val idLeague: String?, val strLeague: String?, val strSport: String?) {
    companion object {
        const val TABLE_LEAGUE: String = "TABLE_LEAGUE"
        const val ID: String = "ID_"
        const val LEAGUE_ID: String = "LEAGUE_ID"
        const val STR_LEAGUE: String = "STR_LEAGUE"
        const val STR_SPORT: String = "STR_SPORT"
    }
}