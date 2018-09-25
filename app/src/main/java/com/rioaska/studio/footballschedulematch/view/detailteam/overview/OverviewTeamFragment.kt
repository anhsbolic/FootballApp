package com.rioaska.studio.footballschedulematch.view.detailteam.overview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rioaska.studio.footballschedulematch.R.id.tvPlayer
import com.rioaska.studio.footballschedulematch.database.TeamDb
import com.rioaska.studio.footballschedulematch.view.home.base.BaseFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class OverviewTeamFragment : BaseFragment(), AnkoComponent<Context> {

    private lateinit var tvDesc: TextView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvDesc.text = activity?.intent?.getStringExtra(TeamDb.STR_DESC_EN)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        scrollView {
            lparams(matchParent, matchParent)

            verticalLayout {
                lparams(matchParent, matchParent)

                tvDesc = textView {
                    id = tvPlayer
                    textSize = sp(5).toFloat()
                }.lparams {
                    width = wrapContent
                    height = wrapContent
                    margin = dip(16)
                }
            }

        }
    }
}