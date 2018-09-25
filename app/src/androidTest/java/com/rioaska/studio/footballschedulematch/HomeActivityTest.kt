package com.rioaska.studio.footballschedulematch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isClickable
import android.support.test.espresso.matcher.ViewMatchers.withId
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
class HomeActivityTest {

    @Rule
    @JvmField
    val mainRule = IntentsTestRule(HomeActivity::class.java)

    @Test
    fun testBottomNavigationClick() {
        onView(withId(action_b_prev)).check(matches(isClickable()))
        onView(withId(action_b_next)).check(matches(isClickable()))
        onView(withId(action_b_fav)).check(matches(isClickable()))
    }

    @Test
    fun testPrevMatchIsDisplayed() {
        onView(withId(action_b_prev)).perform(click())
        onView(withId(R.id.ll_fragment_tab)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testNextMatchIsDisplayed() {
        onView(withId(action_b_next)).perform(click())
        onView(withId(R.id.llTeamList)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testFavMatchIsDisplayed() {
        onView(withId(action_b_fav)).perform(click())
        onView(withId(R.id.ll_fragment_tab)).check(matches(ViewMatchers.isDisplayed()))
    }
}