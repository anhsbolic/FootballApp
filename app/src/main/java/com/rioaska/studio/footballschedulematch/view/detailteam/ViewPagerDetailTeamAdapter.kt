package com.rioaska.studio.footballschedulematch.view.detailteam

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rioaska.studio.footballschedulematch.view.detailteam.overview.OverviewTeamFragment
import com.rioaska.studio.footballschedulematch.view.detailteam.player.PlayersListFragment
import com.rioaska.studio.footballschedulematch.view.home.prevmatch.PrevMatchFragment

/**
 *
 * Created by Rio on 14/09/15.
 * Email rio.aska35@gmail.com
 *
 */
class ViewPagerDetailTeamAdapter(fm: FragmentManager, private var Titles: Array<CharSequence>, private var NumbOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return Titles[position]
    }

    override fun getCount(): Int {
        return NumbOfTabs
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return PlayersListFragment()
            1 -> return OverviewTeamFragment()
        }
        return PrevMatchFragment()
    }
}
