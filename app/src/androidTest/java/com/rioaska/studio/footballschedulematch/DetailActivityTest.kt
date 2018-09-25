package com.rioaska.studio.footballschedulematch

import android.content.Intent
import android.support.design.R.id.snackbar_text
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rioaska.studio.footballschedulematch.R.id.add_to_favorite
import com.rioaska.studio.footballschedulematch.ext.formatDate
import com.rioaska.studio.footballschedulematch.view.detail.DetailActivity
import org.hamcrest.Matchers
import org.junit.After
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
class DetailActivityTest {

    private val date = "2018-09-21"
    private val idEvent = "576511"
    private val idHomeTeam = "133932"
    private val idAwayTeam = "133632"
    private val strHomeTeam = "Huddersfield Town"
    private val strAwayTeam = "Crystal Palace"
    private val strLeague = "English Premier League"

    @Rule
    @JvmField
    val mActivityRule: ActivityTestRule<DetailActivity> =
            object : ActivityTestRule<DetailActivity>(DetailActivity::class.java) {

                override fun getActivityIntent(): Intent {
                    val intent = Intent()
                    intent.putExtra("id", idEvent)
                    intent.putExtra("eventTime", "next")
                    intent.putExtra("home", idHomeTeam)
                    intent.putExtra("away", idAwayTeam)
                    intent.putExtra("homeTeam", strHomeTeam)
                    intent.putExtra("awayTeam", strAwayTeam)
                    intent.putExtra("date", date)
                    intent.putExtra("league", strLeague)
                    return intent
                }
            }

    @Test
    fun testDisplayDetailMatch() {
        onView(withId(R.id.tv_date_detail)).check(matches(ViewMatchers.withText(formatDate(date))))
        onView(withId(R.id.tv_skor_detail)).check(matches(ViewMatchers.withText("VS")))
        onView(withId(R.id.tv_home_team_detail)).check(matches(ViewMatchers.withText("Huddersfield Town")))
        onView(withId(R.id.tv_away_team_detail)).check(matches(ViewMatchers.withText("Crystal Palace")))
        onView(withId(R.id.tv_league_detail)).check(matches(ViewMatchers.withText("English Premier League")))
    }

    @Test
    fun testClickFavoriteAndShowSnackbar() {
        onView(withId(add_to_favorite)).perform(click())
        onView(Matchers.allOf(withId(snackbar_text),
                ViewMatchers.withText(Matchers.containsString("favorite"))))
                .check(matches(ViewMatchers.isDisplayed()))
    }

    @After
    fun deleteFakeMatchDetail() {
        onView(withId(add_to_favorite)).perform(click())
    }
}