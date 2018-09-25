package com.rioaska.studio.footballschedulematch.view.home.favoritetab

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.rioaska.studio.footballschedulematch.view.home.favorite.FavouriteMatchFragment
import com.rioaska.studio.footballschedulematch.view.home.favoriteteam.FavouriteTeamFragment
import com.rioaska.studio.footballschedulematch.view.home.prevmatch.PrevMatchFragment

/**
 *
 * Created by Rio on 14/09/15.
 * Email rio.aska35@gmail.com
 *
 */
class ViewPagerFavAdapter(fm: FragmentManager, private var Titles: Array<CharSequence>, private var NumbOfTabs: Int) : FragmentStatePagerAdapter(fm) {

    override fun getPageTitle(position: Int): CharSequence? {
        return Titles[position]
    }

    override fun getCount(): Int {
        return NumbOfTabs
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return FavouriteMatchFragment()
            1 -> return FavouriteTeamFragment()
        }
        return PrevMatchFragment()
    }
}
