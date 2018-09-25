package com.rioaska.studio.footballschedulematch.view.detailteam.player

import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.model.PlayerResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class PlayerListPresenter(private val view: IPlayerView, private val service: TheSportApi) {

    private val composite = CompositeDisposable()

    fun onDestroy() {
        composite.dispose()
    }

    fun getPlayers(swipeShow: Boolean, id: String) {
        if (!swipeShow)
            view.showLoading(true)
        composite.add(service.getPlayerTeam(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t: PlayerResponse? ->
                    view.showLoading(false)
                    t?.player?.let {
                        view.showPlayerList(it)
                    }
                }, {
                    view.showLoading(false)
                    view.showMessage("${it.message}")
                }))
    }

}