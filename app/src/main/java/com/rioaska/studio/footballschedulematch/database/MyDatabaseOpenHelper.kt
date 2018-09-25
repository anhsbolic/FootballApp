package com.rioaska.studio.footballschedulematch.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 *
 * Created by Rio on 06/09/18.
 * Email rio.aska35@gmail.com
 *
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx,
        "FavouriteTeam.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favourite.TABLE_FAVORITE, true,
                Favourite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favourite.EVENT_ID to TEXT + UNIQUE,
                Favourite.EVENT_DATE to TEXT,
                Favourite.HOME_ID to TEXT,
                Favourite.HOME_NAME to TEXT,
                Favourite.HOME_SCORE to INTEGER,
                Favourite.HOME_GOAL to TEXT,
                Favourite.HOME_LINEUP_GK to TEXT,
                Favourite.HOME_LINEUP_DEF to TEXT,
                Favourite.HOME_LINEUP_MID to TEXT,
                Favourite.HOME_LINEUP_FW to TEXT,
                Favourite.AWAY_ID to TEXT,
                Favourite.AWAY_NAME to TEXT,
                Favourite.AWAY_SCORE to INTEGER,
                Favourite.AWAY_GOAL to TEXT,
                Favourite.AWAY_LINEUP_GK to TEXT,
                Favourite.AWAY_LINEUP_DEF to TEXT,
                Favourite.AWAY_LINEUP_MID to TEXT,
                Favourite.AWAY_LINEUP_FW to TEXT,
                Favourite.EVENT_MATCH to TEXT,
                Favourite.LEAGUE_NAME to TEXT)

        db.createTable(League.TABLE_LEAGUE, true,
                League.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                League.LEAGUE_ID to TEXT + UNIQUE,
                League.STR_LEAGUE to TEXT,
                League.STR_SPORT to TEXT)

        db.createTable(TeamDb.TABLE_TEAM, true,
                TeamDb.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamDb.TEAM_ID to TEXT + UNIQUE,
                TeamDb.STR_TEAM to TEXT,
                TeamDb.FORMED_YEAR to TEXT,
                TeamDb.STR_STADIUM to TEXT,
                TeamDb.STR_WEBSITE to TEXT,
                TeamDb.STR_DESC_EN to TEXT,
                TeamDb.STR_TEAM_BADGE to TEXT,
                TeamDb.STR_TEAM_JERSEY to TEXT,
                TeamDb.STR_TEAM_FANART1 to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favourite.TABLE_FAVORITE, true)
        db.dropTable(League.TABLE_LEAGUE, true)
        db.dropTable(TeamDb.TABLE_TEAM, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

