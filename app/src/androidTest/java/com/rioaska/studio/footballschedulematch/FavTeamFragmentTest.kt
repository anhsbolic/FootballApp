package com.rioaska.studio.footballschedulematch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import com.rioaska.studio.footballschedulematch.R.id.action_b_fav
import com.rioaska.studio.footballschedulematch.R.id.ll_favteam_container
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
class FavTeamFragmentTest {

    @Rule
    @JvmField
    val mainRule = IntentsTestRule(HomeActivity::class.java)

    @Test
    fun testRvBehaviour() {
        onView(withId(action_b_fav)).perform(click())
        onView(withId(R.id.vp_fragment_fav)).perform(ViewActions.swipeLeft())
        onView(withId(ll_favteam_container)).check(matches(isDisplayed()))
    }
}