package com.rioaska.studio.footballschedulematch.view.settingleague

import android.os.Bundle
import android.widget.ProgressBar
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.api.TheSportApi
import com.rioaska.studio.footballschedulematch.view.home.HomeActivity
import com.rioaska.studio.footballschedulematch.view.home.base.BaseActivity
import org.jetbrains.anko.*

/**
 *
 * Created by Rio on 19/09/18.
 * Email rio.aska35@gmail.com
 *
 */
class SettingLeagueActivity : BaseActivity(), ISettingLeagueView {

    private lateinit var presenter: SettingLeaguePresenter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        relativeLayout {
            lparams(matchParent, matchParent)
            fitsSystemWindows = true

            textView("Download Asset League...") {
            }.lparams {
                width = wrapContent
                height = wrapContent
                centerHorizontally()
                margin = dip(10)
                above(R.id.pbSetting)
            }

            progressBar = progressBar {
                id = R.id.pbSetting
            }.lparams {
                width = dip(50)
                height = dip(50)
                centerInParent()
            }
        }

        presenter = SettingLeaguePresenter(this, TheSportApi.instance, this)
        presenter.getLeagueLocal()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showLeagueList() {
        startActivity<HomeActivity>()
        finish()
    }

}

