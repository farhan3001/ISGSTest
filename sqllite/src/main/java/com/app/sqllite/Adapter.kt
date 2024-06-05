package com.app.sqllite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adapter(context: Context, private val items: List<DataModel>) :
    ArrayAdapter<DataModel>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        val item = getItem(position)

        val textView = view.findViewById<TextView>(R.id.textView)
        textView.text = item?.data

        return view
    }
}