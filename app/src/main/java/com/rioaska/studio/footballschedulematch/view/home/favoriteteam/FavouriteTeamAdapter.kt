package com.rioaska.studio.footballschedulematch.view.home.favoriteteam

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.database.TeamDb
import com.rioaska.studio.footballschedulematch.ext.loadImage
import com.rioaska.studio.footballschedulematch.view.home.favoriteteam.FavouriteTeamAdapter.TeamListViewHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 *
 * Created by Rio on 09/09/18.
 * Email rio.aska35@gmail.com
 *
 */
class FavouriteTeamAdapter(private val context: Context,
                           private val match: List<TeamDb>,
                           private val listener: (position: Int) -> Unit) :
        RecyclerView.Adapter<TeamListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListViewHolder {
        return TeamListViewHolder(TeamListUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = match.size

    override fun onBindViewHolder(holder: TeamListViewHolder, position: Int) {
        holder.bindItem(match[position], position)
    }

    inner class TeamListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTeam: TextView = view.find(R.id.tvTitleTeams)
        private val tvDesc: TextView = view.find(R.id.tvDescTeams)
        private val ivTeam: ImageView = view.find(R.id.ivTeam)

        fun bindItem(item: TeamDb, pos: Int) {
            tvTeam.text = item.strTeam
            tvDesc.text = item.strDescriptionEN
            loadImage(context, item.strTeamBadge ?: "", ivTeam)
            itemView.onClick {
                listener(pos)
            }
        }
    }

    class TeamListUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                frameLayout {
                    lparams(matchParent, wrapContent)

                    relativeLayout {

                        imageView {
                            id = R.id.ivTeam
                        }.lparams {
                            width = dip(70)
                            height = dip(70)
                            margin = dip(10)
                            alignParentLeft()
                        }

                        verticalLayout {

                            textView {
                                id = R.id.tvTitleTeams
                                textSize = sp(6).toFloat()
                            }.lparams {
                                width = wrapContent
                                height = wrapContent
                            }

                            textView {
                                textSize = sp(4).toFloat()
                                id = R.id.tvDescTeams
                                textColor = ContextCompat.getColor(ctx, R.color.secondaryText)
                                maxLines = 2
                                minLines = 2
                                ellipsize = TextUtils.TruncateAt.END
                                gravity = Gravity.CENTER_HORIZONTAL
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                            }

                        }.lparams {
                            width = matchParent
                            height = wrapContent
                            centerVertically()
                            rightOf(R.id.ivTeam)

                        }

                    }.lparams {
                        width = matchParent
                        height = wrapContent
                        margin = dip(10)
                    }


                }
            }

        }
    }

}