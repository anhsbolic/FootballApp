package com.rioaska.studio.footballschedulematch.database

/**
 *
 * Created by Rio on 06/09/18.
 * Email rio.aska35@gmail.com
 *
 */
data class TeamDb(val id: Long?, val idTeam: String?, val strTeam: String?, val intFormedYear: String?,
                  val strStadium: String?, val strWebsite: String?, val strDescriptionEN: String?,
                  val strTeamBadge: String?, val strTeamJersey: String?, val strTeamFanart1: String?) {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val STR_TEAM: String = "STR_TEAM"
        const val FORMED_YEAR: String = "FORMED_YEAR"
        const val STR_STADIUM: String = "STR_STADIUM"
        const val STR_WEBSITE: String = "STR_WEBSITE"
        const val STR_DESC_EN: String = "STR_DESC_EN"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_TEAM_JERSEY: String = "STR_TEAM_JERSEY"
        const val STR_TEAM_FANART1: String = "STR_TEAM_FANART1"
    }
}