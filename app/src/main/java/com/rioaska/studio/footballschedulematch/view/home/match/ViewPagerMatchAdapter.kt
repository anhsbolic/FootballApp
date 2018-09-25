package com.rioaska.studio.footballschedulematch.view.home.match

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rioaska.studio.footballschedulematch.view.home.nextmatch.NextMatchFragment
import com.rioaska.studio.footballschedulematch.view.home.prevmatch.PrevMatchFragment

/**
 *
 * Created by Rio on 14/09/15.
 * Email rio.aska35@gmail.com
 *
 */
class ViewPagerMatchAdapter(fm: FragmentManager, private var Titles: Array<CharSequence>, private var NumbOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return Titles[position]
    }

    override fun getCount(): Int {
        return NumbOfTabs
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return PrevMatchFragment()
            1 -> return NextMatchFragment()
        }
        return PrevMatchFragment()
    }
}
