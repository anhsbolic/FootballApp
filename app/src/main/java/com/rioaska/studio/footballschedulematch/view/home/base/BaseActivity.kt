package com.rioaska.studio.footballschedulematch.view.home.base

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager
import com.rioaska.studio.footballschedulematch.model.League
import com.rioaska.studio.footballschedulematch.util.Cons

/**
 *
 * Created by Rio on 19/09/18.
 * Email rio.aska35@gmail.com
 *
 */
open class BaseActivity : AppCompatActivity() {

    protected lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences(Cons.SHARED_PREF, Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        sharedPref = getSharedPreferences(Cons.SHARED_PREF, Context.MODE_PRIVATE)
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

    fun getIdLeague(): String = sharedPref.getString(Cons.ID_LEAGUE, "4328")

    fun getPositionChoicePrev(): Int = sharedPref.getInt(Cons.POSITION_CHOICE, 1)

    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val v = currentFocus
        if (v != null) {
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val v = currentFocus
        if (v != null)
            imm.showSoftInput(v, 0)
    }

    fun back() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}