package com.rioaska.studio.footballschedulematch.view.detailplayer

import android.R.id.home
import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.ext.loadImage
import com.rioaska.studio.footballschedulematch.ext.loadImageCircle
import com.rioaska.studio.footballschedulematch.util.Cons
import com.rioaska.studio.footballschedulematch.view.home.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail_player.*

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class DetailPlayerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        init()
    }

    private fun init() {
        setSupportActionBar(toolbar_player_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        tv_name_player.text = intent.getStringExtra(Cons.NAME_PLAYER)
        tv_position_player.text = intent.getStringExtra(Cons.POSITION_PLAYER)
        tv_height_player.text = intent.getStringExtra(Cons.HEIGHT_PLAYER)
        tv_weight_player.text = intent.getStringExtra(Cons.WEIGHT_PLAYER)
        tv_desc_player.text = intent.getStringExtra(Cons.DESC_PLAYER)

        if (!isFinishing) {
            if (!intent.getStringExtra(Cons.COVER_PLAYER).isNullOrEmpty()) {
                loadImage(this, intent.getStringExtra(Cons.COVER_PLAYER), iv_cover_player_detail)
            }
            if (!intent.getStringExtra(Cons.BADGE_PLAYER).isNullOrEmpty()) {
                loadImageCircle(this, intent.getStringExtra(Cons.BADGE_PLAYER), iv_badge_player)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            home -> {
                setResult(Activity.RESULT_OK)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
