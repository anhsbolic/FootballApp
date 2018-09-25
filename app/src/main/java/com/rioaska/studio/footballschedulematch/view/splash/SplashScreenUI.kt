package com.rioaska.studio.footballschedulematch.view.splash

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import com.rioaska.studio.footballschedulematch.R
import org.jetbrains.anko.*

/**
 *
 * Created by Rio on 29/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class SplashScreenUI : AnkoComponent<SplashScreenActivity> {
    override fun createView(ui: AnkoContext<SplashScreenActivity>): View = with(ui) {
        return relativeLayout {
            lparams(matchParent, matchParent)
            fitsSystemWindows = true
            backgroundDrawable = context.getDrawable(R.drawable.img_splash_bg)

            imageView {
                setImageResource(R.drawable.ic_schedule)
                animation = AnimationUtils.loadAnimation(context, R.anim.move_left)
                startAnimation(animation)
            }.lparams {
                width = dip(100)
                height = dip(100)
                alignParentTop()
                centerHorizontally()
                topMargin = dip(100)
            }

            verticalLayout {

                textView {
                    textSize = sp(12).toFloat()
                    text = context.getString(R.string.app_name)
                    textColor = Color.parseColor("#FFFFFF")
                    gravity = Gravity.CENTER_HORIZONTAL
                    animation = AnimationUtils.loadAnimation(context, R.anim.move_right)
                    startAnimation(animation)
                }.lparams {
                    width = matchParent
                    height = wrapContent
                }

                textView {
                    textSize = sp(6).toFloat()
                    text = context.getString(R.string.text_splash_screen)
                    textColor = Color.parseColor("#FFFFFF")
                    gravity = Gravity.CENTER_HORIZONTAL
                    animation = AnimationUtils.loadAnimation(context, R.anim.move_left)
                    startAnimation(animation)
                }.lparams {
                    width = matchParent
                    height = wrapContent
                    topMargin = dip(16)
                }
            }.lparams {
                width = matchParent
                height = wrapContent
                alignParentBottom()
                bottomMargin = dip(50)
            }
        }
    }
}