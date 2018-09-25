package com.rioaska.studio.footballschedulematch.view.home.prevmatch

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.ext.formatDate
import com.rioaska.studio.footballschedulematch.model.ItemSchedule
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class PrevMatchAdapter(private val match: List<ItemSchedule>,
                       private val listener: (position: Int) -> Unit)
    : RecyclerView.Adapter<PrevMatchAdapter.PrevMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevMatchViewHolder {
        return PrevMatchViewHolder(PrevMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PrevMatchViewHolder, position: Int) {
        holder.bindItem(match[position], position)
    }

    override fun getItemCount(): Int = match.size


    inner class PrevMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDate: TextView = view.find(R.id.tvDatePastMatch)
        private val tvTeamHome: TextView = view.find(R.id.tvHomeTeamPast)
        private val tvTeamAway: TextView = view.find(R.id.tvAwayTeamPast)
        private val tvHomeSkor: TextView = view.find(R.id.tvHomeSkorPast)
        private val tvAwaySkor: TextView = view.find(R.id.tvAwaySkorPast)

        fun bindItem(item: ItemSchedule, pos: Int) {
            tvTeamHome.text = item.strHomeTeam
            tvTeamAway.text = item.strAwayTeam
            tvHomeSkor.text = item.intHomeScore
            tvAwaySkor.text = item.intAwayScore
            tvDate.text = formatDate(item.dateEvent ?: "")

            itemView.onClick {
                listener(pos)
            }
        }

    }

    class PrevMatchUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                frameLayout {
                    lparams(matchParent, wrapContent)

                    cardView {
                        layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply {
                            leftMargin = dip(10)
                            rightMargin = dip(10)
                            topMargin = dip(5)
                            bottomMargin = dip(5)
                        }
                        background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                        radius = dip(8).toFloat()

                        verticalLayout {
                            padding = dip(8)

                            textView {
                                textSize = sp(4).toFloat()
                                id = R.id.tvDatePastMatch
                                textColor = ContextCompat.getColor(ctx, R.color.colorText)
                                gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                            }

                            linearLayout {

                                textView {
                                    textSize = sp(6).toFloat()
                                    id = R.id.tvHomeTeamPast
                                    textColor = ContextCompat.getColor(ctx, R.color.secondaryText)
                                    maxLines = 1
                                    ellipsize = TextUtils.TruncateAt.END
                                    gravity = Gravity.CENTER_HORIZONTAL
                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    weight = 1F
                                }

                                linearLayout {
                                    gravity = Gravity.CENTER_HORIZONTAL

                                    textView {
                                        textSize = sp(8).toFloat()
                                        id = R.id.tvHomeSkorPast
                                        typeface = Typeface.DEFAULT_BOLD
                                        textColor = ContextCompat.getColor(ctx, R.color.primaryText)
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams {
                                        width = wrapContent
                                        height = wrapContent
                                    }

                                    textView {
                                        textSize = sp(6).toFloat()
                                        text = ctx.getString(R.string.versus)
                                        textColor = ContextCompat.getColor(ctx, R.color.primaryText)
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams {
                                        width = wrapContent
                                        height = wrapContent
                                        leftMargin = dip(15)
                                        rightMargin = dip(15)
                                    }

                                    textView {
                                        textSize = sp(8).toFloat()
                                        id = R.id.tvAwaySkorPast
                                        typeface = Typeface.DEFAULT_BOLD
                                        textColor = ContextCompat.getColor(ctx, R.color.primaryText)
                                        gravity = Gravity.CENTER_HORIZONTAL
                                    }.lparams {
                                        width = wrapContent
                                        height = wrapContent
                                    }

                                }.lparams {
                                    width = matchParent
                                    height = wrapContent

                                }.lparams {
                                    width = matchParent
                                    height = wrapContent
                                    weight = 1F
                                }

                                textView {
                                    textSize = sp(6).toFloat()
                                    id = R.id.tvAwayTeamPast
                                    maxLines = 1
                                    ellipsize = TextUtils.TruncateAt.END
                                    gravity = Gravity.CENTER_HORIZONTAL
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
                                bottomMargin = dip(10)
                            }

                        }
                    }
                }
            }

        }
    }
}