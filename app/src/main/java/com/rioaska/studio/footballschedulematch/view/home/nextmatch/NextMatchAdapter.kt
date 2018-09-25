package com.rioaska.studio.footballschedulematch.view.home.nextmatch

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.R.id.*
import com.rioaska.studio.footballschedulematch.ext.formatDate
import com.rioaska.studio.footballschedulematch.model.ItemSchedule
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class NextMatchAdapter(private val match: List<ItemSchedule>,
                       private val listener: (position: Int) -> Unit,
                       private val listenerBell: (position: Int) -> Unit)
    : RecyclerView.Adapter<NextMatchAdapter.NextMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        return NextMatchViewHolder(NextMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bindItem(match[position], position)
    }

    override fun getItemCount(): Int = match.size


    inner class NextMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDate: TextView = view.find(tvDatePastMatch)
        private val tvTeamHome: TextView = view.find(tvHomeTeamPast)
        private val tvTeamAway: TextView = view.find(tvAwayTeamPast)
        private val llContent: LinearLayout = view.find(ll_content_next)
        private val ivClickBell: ImageView = view.find(ivBell)

        fun bindItem(item: ItemSchedule, pos: Int) {
            tvTeamHome.text = item.strHomeTeam
            tvTeamAway.text = item.strAwayTeam
            tvDate.text = formatDate(item.dateEvent ?: "")

            llContent.onClick { listener(pos) }
            ivClickBell.onClick { listenerBell(pos) }
        }

    }

    class NextMatchUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {

                frameLayout {
                    lparams(matchParent, wrapContent)

                    verticalLayout {
                        padding = dip(8)
                        backgroundDrawable = ctx.getDrawable(R.drawable.border_bottom)

                        textView {
                            textSize = sp(4).toFloat()
                            id = tvDatePastMatch
                            textColor = ContextCompat.getColor(ctx, R.color.colorText)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }.lparams {
                            width = matchParent
                            height = wrapContent
                        }

                        linearLayout {
                            id = ll_content_next
                            textView {
                                textSize = sp(6).toFloat()
                                id = tvHomeTeamPast
                                textColor = ContextCompat.getColor(ctx, R.color.secondaryText)
                                maxLines = 1
                                ellipsize = TextUtils.TruncateAt.END
                                gravity = Gravity.CENTER
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                                weight = 1F
                            }

                            textView {
                                textSize = sp(8).toFloat()
                                textColor = ContextCompat.getColor(ctx, R.color.primaryText)
                                text = ctx.getString(R.string.versus)
                                gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                                weight = 1F
                            }

                            textView {
                                textSize = sp(6).toFloat()
                                id = tvAwayTeamPast
                                maxLines = 1
                                ellipsize = TextUtils.TruncateAt.END
                                gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(ctx, R.color.secondaryText)
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                                weight = 1F
                            }

                        }.lparams {
                            width = matchParent
                            height = wrapContent
                            topMargin = dip(10)
                        }

                        imageView {
                            id = ivBell
                            backgroundDrawable = ctx.getDrawable(R.drawable.v_ic_bell)
                        }.lparams {
                            width = dip(20)
                            height = dip(20)
                            margin = dip(10)
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                    }.lparams {
                        width = matchParent
                        height = wrapContent
                    }
                }
            }
        }
    }
}