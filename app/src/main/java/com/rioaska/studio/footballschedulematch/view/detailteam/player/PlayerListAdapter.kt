package com.rioaska.studio.footballschedulematch.view.detailteam.player

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.rioaska.studio.footballschedulematch.R
import com.rioaska.studio.footballschedulematch.R.id.*
import com.rioaska.studio.footballschedulematch.ext.loadImageCircle
import com.rioaska.studio.footballschedulematch.model.Player
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class PlayerListAdapter(private val context: Context,
                        private val players: List<Player>,
                        private val listener: (position: Int) -> Unit)
    : RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListViewHolder {
        return PlayerListViewHolder(PlayerListUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: PlayerListViewHolder, position: Int) {
        holder.bindItem(players[position], position)
    }

    override fun getItemCount(): Int = players.size

    inner class PlayerListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNamePlayer: TextView = view.find(tvPlayer)
        private val tvPosition: TextView = view.find(tvPositionPlayer)
        private val imagePlayer: ImageView = view.find(R.id.ivPlayer)

        fun bindItem(item: Player, pos: Int) {
            tvNamePlayer.text = item.strPlayer
            tvPosition.text = item.strPosition
            loadImageCircle(context, item.strCutout ?: "", imagePlayer)
            itemView.onClick {
                listener(pos)
            }
        }

    }

    class PlayerListUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                frameLayout {
                    lparams(matchParent, wrapContent)

                    relativeLayout {

                        imageView {
                            id = ivPlayer
                        }.lparams {
                            width = dip(50)
                            height = dip(70)
                            margin = dip(10)
                            alignParentLeft()
                        }

                        verticalLayout {

                            textView {
                                id = tvPlayer
                                textSize = sp(6).toFloat()
                            }.lparams {
                                width = wrapContent
                                height = wrapContent
                            }

                            textView {
                                textSize = sp(4).toFloat()
                                id = R.id.tvPositionPlayer
                                textColor = ContextCompat.getColor(ctx, R.color.secondaryText)
                            }.lparams {
                                width = matchParent
                                height = wrapContent
                            }

                        }.lparams {
                            width = matchParent
                            height = wrapContent
                            centerVertically()
                            rightOf(ivPlayer)
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