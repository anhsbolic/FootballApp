package com.rioaska.studio.footballschedulematch.view.detail

import com.rioaska.studio.footballschedulematch.api.TheSportApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Rio on 31/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class DetailMatchPresenter(private val view: IDetailMatchView, private val service: TheSportApi) {

    private val composite = CompositeDisposable()
    private val HOME = 1

    fun onDestroy() {
        composite.dispose()
    }

    fun getDetailMatch(id: String) {
        view.showLoading(true)
        composite.add(service.getMatchDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    view.showLoading(false)
                    t?.events?.let { view.showDetailMatch(it[0]) }
                }, {
                    view.showLoading(false)
                    view.showMessage("${it.message}")
                    view.showErrorLayout(true)
                }))
    }

    fun getBadge(id: String, team: Int) {
        composite.add(service.getDetailTeam(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    t?.teams?.let {
                        if (team == HOME)
                            view.showBadgeImageHome(it[0].strTeamBadge ?: "")
                        else
                            view.showBadgeImageAway(it[0].strTeamBadge ?: "")
                    }
                }, {

                }))
    }

}