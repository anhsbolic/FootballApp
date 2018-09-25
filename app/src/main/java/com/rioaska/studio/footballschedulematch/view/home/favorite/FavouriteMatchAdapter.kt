package com.rioaska.studio.footballschedulematch.view.home.favorite

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.database.Favourite
import com.rioaska.studio.footballschedulematch.ext.formatDate
import com.rioaska.studio.footballschedulematch.view.detail.DetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

/**
 *
 * Created by Rio on 09/09/18.
 * Email rio.aska35@gmail.com
 *
 */
class FavouriteMatchAdapter(private val context: Context, private val match: List<Favourite>) :
        RecyclerView.Adapter<FavouriteMatchAdapter.FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(FavoriteMatchUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(match[position])
        holder.itemView.setOnClickListener {
            context.startActivity<DetailActivity>("id" to match[position].eventId,
                    "eventTime" to "local",
                    "home" to match[position].homeId,
                    "away" to match[position].awayId)
        }
    }


    class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDate: TextView = view.find(R.id.tvDatePastMatch)
        private val tvTeamHome: TextView = view.find(R.id.tvHomeTeamPast)
        private val tvTeamAway: TextView = view.find(R.id.tvAwayTeamPast)
        private val tvHomeSkor: TextView = view.find(R.id.tvHomeSkorPast)
        private val tvAwaySkor: TextView = view.find(R.id.tvAwaySkorPast)

        fun bindItem(item: Favourite) {
            tvTeamHome.text = item.homeName
            tvTeamAway.text = item.awayName
            if (item.eventMatch == "next") {
                tvHomeSkor.visibility = INVISIBLE
                tvAwaySkor.visibility = INVISIBLE
            } else {
                tvHomeSkor.text = "${item.homeScore}"
                tvAwaySkor.text = "${item.awayScore}"
            }
            tvDate.text = formatDate(item.eventDate ?: "")
        }

    }

    class FavoriteMatchUI : AnkoComponent<ViewGroup> {
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