package com.rioaska.studio.footballschedulematch.view.home.favorite

import android.content.Context
import com.rioaska.studio.footballschedulematch.database.Favourite
import com.rioaska.studio.footballschedulematch.database.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 *
 * Created by Rio on 30/08/18.
 * Email rio.aska35@gmail.com
 *
 */
class FavMatchPresenter(private val view: IMatchFavView, private val context: Context) {

    fun getMatch() {
        context.database.use {
            val result = select(Favourite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favourite>())
            view.showMatchList(favorite)
            if (favorite.isEmpty()) {
                view.showNoData(true)
            } else {
                view.showNoData(false)
            }
        }
    }
}