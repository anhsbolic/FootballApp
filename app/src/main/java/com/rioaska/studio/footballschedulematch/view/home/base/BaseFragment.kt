package com.rioaska.studio.footballschedulematch.view.home.base

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import com.rioaska.studio.footballschedulematch.database.League
import com.rioaska.studio.footballschedulematch.util.Cons
import org.jetbrains.anko.support.v4.ctx

/**
 *
 * Created by Rio on 19/09/18.
 * Email rio.aska35@gmail.com
 *
 */
open class BaseFragment : Fragment() {

    protected lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = ctx.getSharedPreferences(Cons.SHARED_PREF, Context.MODE_PRIVATE)
    }

    fun getSharedPreferences(): SharedPreferences = sharedPref

    fun saveLeague(item: League) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(Cons.ID_LEAGUE, item.idLeague)
        editor.putString(Cons.STR_LEAGUE, item.strLeague)
        editor.apply()
    }

    fun savePositionPrevMatch(position: Int) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putInt(Cons.POSITION_CHOICE, position)
        editor.apply()
    }


    fun getIdLeague(): String = sharedPref.getString(Cons.ID_LEAGUE, "")

    fun getPositionChoicePrev(): Int = sharedPref.getInt(Cons.POSITION_CHOICE, 1)
}