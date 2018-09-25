package com.rioaska.studio.footballschedulematch.view.searchteam

import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.model.TeamResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class SearchTeamListPresenter(private val view: ISearchView, private val service: TheSportApi) {

    private val composite = CompositeDisposable()

    fun onDestroy() {
        composite.dispose()
    }

    fun getSearchTeams(id: String) {
        view.showLoading(true)
        composite.add(service.searchTeam(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: TeamResponse? ->
                    view.showLoading(false)
                    t?.teams?.let {
                        view.showTeamList(it)
                    }
                }, {
                    view.showLoading(false)
                    view.showMessage("${it.message}")
                }))
    }

}