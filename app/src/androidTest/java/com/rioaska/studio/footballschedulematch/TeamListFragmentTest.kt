package com.rioaska.studio.footballschedulematch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.rioaska.studio.footballschedulematch.R.id.*
import com.rioaska.studio.footballschedulematch.view.detailteam.DetailTeamActivity
import com.rioaska.studio.footballschedulematch.view.home.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 *
 * Created by Rio on 12/09/18.
 * Email rio.aska35@gmail.com
 *
 */
@RunWith(AndroidJUnit4::class)
class TeamListFragmentTest {

    @Rule
    @JvmField
    val mainRule = IntentsTestRule(HomeActivity::class.java)

    @Test
    fun testRvBehaviour() {
        onView(withId(action_b_next)).perform(click())
        onView(withId(llTeamList))
                .check(matches(isDisplayed()))
        onView(withId(rvTeams))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

        val rand = Random()
        val itemPos = rand.nextInt(14)

        onView(withId(rvTeams)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemPos))

        onView(withId(rvTeams))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(itemPos, click()))
        intended(IntentMatchers.hasComponent(DetailTeamActivity::class.java.name))
    }
}