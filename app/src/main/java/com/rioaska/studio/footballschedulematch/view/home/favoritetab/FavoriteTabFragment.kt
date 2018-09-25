package com.rioaska.studio.footballschedulematch.view.home.favoritetab

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.view.home.base.BaseFragment

/**
 *
 * Created by Rio on 19/09/18.
 * Email rio.aska35@gmail.com
 *
 */
class FavoriteTabFragment : BaseFragment() {

    private val titles = arrayOf<CharSequence>("Match", "Team")
    private val numbOfTabs = 2
    private lateinit var tab: TabLayout
    private lateinit var vp: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab_fav, container, false)
        tab = view.findViewById(R.id.tab_layout_fav)
        vp = view.findViewById(R.id.vp_fragment_fav)

        val adapter = ViewPagerFavAdapter(activity?.supportFragmentManager!!, titles, numbOfTabs)
        vp.adapter = adapter
        tab.setupWithViewPager(vp)

        return view
    }

}