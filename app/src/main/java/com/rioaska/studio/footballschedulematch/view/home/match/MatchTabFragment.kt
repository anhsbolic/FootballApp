package com.rioaska.studio.footballschedulematch.view.home.match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.view.home.base.BaseFragment

/**
 *
 * Created by Rio on 19/09/18.
 * Email rio.aska35@gmail.com
 *
 *
 */
class MatchTabFragment : BaseFragment() {

    private val titles = arrayOf<CharSequence>("Past", "Next")
    private val numbOfTabs = 2
    private lateinit var tab: TabLayout
    private lateinit var vp: ViewPager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tab, container, false)
        tab = view.findViewById(R.id.tab_layout)
        vp = view.findViewById(R.id.vp_fragment)

        val adapter = ViewPagerMatchAdapter(activity?.supportFragmentManager!!, titles, numbOfTabs)
        vp.adapter = adapter
        tab.setupWithViewPager(vp)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)

        val menuSearch = menu?.findItem(R.id.action_search_match)
        menuSearch?.isVisible = true
    }

}