package com.rioaska.studio.footballschedulematch.view.splash

import android.os.Bundle
import android.os.Handler
import com.rioaska.studio.footballschedulematch.view.home.base.BaseActivity
import com.rioaska.studio.footballschedulematch.view.settingleague.SettingLeagueActivity
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

/**
 *
 * Created by Rio on 29/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplashScreenUI().setContentView(this)
        goHome()
    }

    private fun goHome() {
        val handler = Handler()
        handler.postDelayed({
            startActivity<SettingLeagueActivity>()
            finish()
        }, 3000)
    }
}