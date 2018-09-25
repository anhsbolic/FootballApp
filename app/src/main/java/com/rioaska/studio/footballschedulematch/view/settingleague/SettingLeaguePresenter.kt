package com.rioaska.studio.footballschedulematch.view.settingleague

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.database.League
import com.rioaska.studio.footballschedulematch.database.database
import com.rioaska.studio.footballschedulematch.model.LeagueResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class SettingLeaguePresenter(private val view: ISettingLeagueView, private val service: TheSportApi,
                             private val context: Context) {

    private val composite = CompositeDisposable()

    fun onDestroy() {
        composite.dispose()
    }

    private fun getLeague() {
        composite.add(service.getAllLeague()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: LeagueResponse? ->
                    t?.leagues?.let {
                        saveLeague(it)
                    }
                }, {
                    view.showMessage("${it.message}")
                }))
    }

    private fun saveLeague(item: MutableList<com.rioaska.studio.footballschedulematch.model.League>) {
        try {
            context.database.use {
                for (liga in item) {
                    insert(com.rioaska.studio.footballschedulematch.database.League.TABLE_LEAGUE,
                            League.LEAGUE_ID to liga.idLeague,
                            League.STR_LEAGUE to liga.strLeague,
                            League.STR_SPORT to liga.strSport)
                }
                view.showLeagueList()
            }
        } catch (e: SQLiteConstraintException) {
            view.showMessage(e.localizedMessage)
        }
    }

    fun getLeagueLocal() {
        context.database.use {
            val result = select(League.TABLE_LEAGUE)
            val liga = result.parseList(classParser<League>())
            if (liga.isEmpty()) {
                getLeague()
            } else {
                view.showLeagueList()
            }
        }
    }
}