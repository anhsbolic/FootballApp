package com.rioaska.studio.footballschedulematch.ext

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * Created by Rio on 29/08/18.
 * Email rio.aska35@gmail.com
 *
 */
internal fun loadImage(context: Context,
                       url: String,
                       imageView: ImageView) {

    fun setMemoryCategory(context: Context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
    }

    setMemoryCategory(context)

    GlideApp.with(context)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
}

internal fun loadImageCircle(context: Context,
                             url: String,
                             imageView: ImageView) {

    fun setMemoryCategory(context: Context) {
        Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
    }

    setMemoryCategory(context)

    GlideApp.with(context)
            .load(url)
            .centerCrop()
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(imageView)
}

internal fun formatDate(dateEvent: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date = format.parse(dateEvent)
    return SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
            .format(date).toString()
}

internal fun checkBeforeDate(dateEvent: String): Boolean {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return !format.parse(dateEvent).before(Date())

}

internal fun String?.changeLineDetailMatch(): String? {
    return this?.replace(";", "\n")?.replace(":", " ")
}
