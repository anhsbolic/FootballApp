package com.rioaska.studio.footballschedulematch

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rioaska.studio.footballschedulematch.util.Cons
import com.rioaska.studio.footballschedulematch.view.detailplayer.DetailPlayerActivity
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
class DetailPlayerActivityTest {

    private val name = "Álvaro Morata"
    private val position = "Forward"
    private val height = "1.87m"
    private val weight = "82Kg"
    private val desc = "lvaro Borja Morata Martín (Spanish pronunciation: ; born 23 October 1992) is a Spanish professional footballer who plays as a striker for Chelsea and the Spain national team. He began his career at Real Madrid, making his debut with the senior team in late 2010 and going on to appear in 52 official games (11 goals), notably winning the 2014 Champions League. He moved to Juventus for €20 million in 2014, winning the domestic double of Serie A and Coppa Italia in both of his seasons before being bought back for €30 million. Morata earned 34 caps for Spain at youth level, helping the country win the 2013 European Under-21 Championship. He made his senior debut in 2014, representing the nation at the Euro 2016."

    @Rule
    @JvmField
    val mActivityRule: ActivityTestRule<DetailPlayerActivity> =
            object : ActivityTestRule<DetailPlayerActivity>(DetailPlayerActivity::class.java) {

                override fun getActivityIntent(): Intent {
                    val intent = Intent()
                    intent.putExtra(Cons.NAME_PLAYER, name)
                    intent.putExtra(Cons.COVER_PLAYER, name)
                    intent.putExtra(Cons.BADGE_PLAYER, name)
                    intent.putExtra(Cons.POSITION_PLAYER, position)
                    intent.putExtra(Cons.DESC_PLAYER, desc)
                    intent.putExtra(Cons.HEIGHT_PLAYER, height)
                    intent.putExtra(Cons.WEIGHT_PLAYER, weight)
                    return intent
                }
            }

    @Test
    fun testDisplayDetailPlayer() {
        onView(withId(R.id.tv_name_player)).check(matches(ViewMatchers.withText("Álvaro Morata")))
        onView(withId(R.id.tv_position_player)).check(matches(ViewMatchers.withText("Forward")))
        onView(withId(R.id.tv_height_player)).check(matches(ViewMatchers.withText("1.87m")))
        onView(withId(R.id.tv_weight_player)).check(matches(ViewMatchers.withText("82Kg")))
        onView(withId(R.id.tv_desc_player)).check(matches(ViewMatchers.withText("lvaro Borja Morata Martín (Spanish pronunciation: ; born 23 October 1992) is a Spanish professional footballer who plays as a striker for Chelsea and the Spain national team. He began his career at Real Madrid, making his debut with the senior team in late 2010 and going on to appear in 52 official games (11 goals), notably winning the 2014 Champions League. He moved to Juventus for €20 million in 2014, winning the domestic double of Serie A and Coppa Italia in both of his seasons before being bought back for €30 million. Morata earned 34 caps for Spain at youth level, helping the country win the 2013 European Under-21 Championship. He made his senior debut in 2014, representing the nation at the Euro 2016.")))
    }

}