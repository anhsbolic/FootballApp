package com.rioaska.studio.footballschedulematch.database

/**
 *
 * Created by Rio on 06/09/18.
 * Email rio.aska35@gmail.com
 *
 */
data class Favourite(val id: Long?, val eventId: String?, val eventDate: String?,
                     val homeId: String?, val homeName: String?, val homeScore: Int?, val homeGoal: String?,
                     val homeLineupGk: String?, val homeLineupDef: String?, val homeLineupMid: String?, val homeLineupFW: String?,
                     val awayId: String?, val awayName: String?, val awayScore: Int?, val awayGoal: String?,
                     val awayLineupGk: String?, val awayLineupDef: String?, val awayLineupMid: String?, val awayLineupFW: String?,
                     val eventMatch: String?, val leagueName: String?) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_ID: String = "HOME_ID"
        const val HOME_NAME: String = "HOME_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val HOME_GOAL: String = "HOME_GOAL"
        const val HOME_LINEUP_GK: String = "HOME_LINEUP_GK"
        const val HOME_LINEUP_DEF: String = "HOME_LINEUP_DEF"
        const val HOME_LINEUP_MID: String = "HOME_LINEUP_MID"
        const val HOME_LINEUP_FW: String = "HOME_LINEUP_FW"
        const val AWAY_ID: String = "AWAY_ID"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val AWAY_GOAL: String = "AWAY_GOAL"
        const val AWAY_LINEUP_GK: String = "AWAY_LINEUP_GK"
        const val AWAY_LINEUP_DEF: String = "AWAY_LINEUP_DEF"
        const val AWAY_LINEUP_MID: String = "AWAY_LINEUP_MID"
        const val AWAY_LINEUP_FW: String = "AWAY_LINEUP_FW"
        const val EVENT_MATCH: String = "EVENT_MATCH"
        const val LEAGUE_NAME: String = "LEAGUE_NAME"
    }
}