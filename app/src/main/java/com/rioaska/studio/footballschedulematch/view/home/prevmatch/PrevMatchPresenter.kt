package com.rioaska.studio.footballschedulematch.view.home.prevmatch

import android.content.Context
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.database.League
import com.rioaska.studio.footballschedulematch.database.database
import com.rioaska.studio.footballschedulematch.model.ScheduleResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class PrevMatchPresenter(private val view: IMatchView, private val service: TheSportApi,
                         private val context: Context) {

    private val composite = CompositeDisposable()

    fun onDestroy() {
        composite.dispose()
    }

    fun getMatch(swipeShow: Boolean, id: String) {
        if (!swipeShow)
            view.showLoading(true)
        composite.add(service.getPrevMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: ScheduleResponse? ->
                    view.showLoading(false)
                    t?.events?.let {
                        view.showMatchList(it)
                    }
                }, {
                    view.showLoading(false)
                    view.showMessage("${it.message}")
                }))
    }

    fun getLeague() {
        context.database.use {
            val result = select(League.TABLE_LEAGUE)
            val league = result.parseList(classParser<League>())
            view.showLeagueList(league)
            if (league.isEmpty()) {
                view.showSpinner(false)
            } else {
                view.showSpinner(true)
            }
        }
    }

}