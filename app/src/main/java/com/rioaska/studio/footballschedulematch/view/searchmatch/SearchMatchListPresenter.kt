package com.rioaska.studio.footballschedulematch.view.searchmatch

import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.model.ScheduleResponse
import com.rioaska.studio.footballschedulematch.model.SearchMatchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class SearchMatchListPresenter(private val view: ISearchMatchView, private val service: TheSportApi) {

    private val composite = CompositeDisposable()

    fun onDestroy() {
        composite.dispose()
    }

    fun getSearchMatch(id: String) {
        view.showLoading(true)
        composite.add(service.searchMatch(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: SearchMatchResponse? ->
                    view.showLoading(false)
                    t?.events?.let {
                        view.showMatchList(it)
                    }
                }, {
                    view.showLoading(false)
                    view.showMessage("${it.message}")
                }))
    }

}