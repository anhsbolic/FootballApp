package com.rioaska.studio.footballschedulematch.view.settingleague

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.rioaska.studio.footballschedulematch.database.League


/**
 * Created by Rio on 19/09/18.
 * Email rio.aska35@gmail.com
 */
class SpinnerAdapter(context: Context, textViewResourceId: Int,
                     private val values: MutableList<League>) : ArrayAdapter<League>(context, textViewResourceId, values) {

    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): League {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val label = super.getView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = values[position].strLeague
        return label
    }

    override fun getDropDownView(position: Int, convertView: View?,
                                 parent: ViewGroup): View? {
        val label = super.getDropDownView(position, convertView, parent) as TextView
        label.setTextColor(Color.BLACK)
        label.text = values[position].strLeague

        return label
    }
}
