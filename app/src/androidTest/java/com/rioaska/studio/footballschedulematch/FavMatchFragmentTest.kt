package com.rioaska.studio.footballschedulematch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import com.rioaska.studio.footballschedulematch.R.id.*
import com.rioaska.studio.footballschedulematch.view.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *
 * Created by Rio on 12/09/18.
 * Email rio.aska35@gmail.com
 *
 */
@RunWith(AndroidJUnit4::class)
class FavMatchFragmentTest {

    @Rule
    @JvmField
    val mainRule = IntentsTestRule(HomeActivity::class.java)

    @Test
    fun testRvBehaviour() {
        onView(withId(action_b_fav)).perform(click())
        onView(withId(ll_fav_container))
                .check(matches(isDisplayed()))
        onView(withId(rvFav))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}