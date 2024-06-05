package com.app.restapi

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
        val item = items[position]

        val textViewUserId = view.findViewById<TextView>(R.id.textViewUserId)
        val textViewId = view.findViewById<TextView>(R.id.textViewId)
        val textViewTitle = view.findViewById<TextView>(R.id.textViewTitle)
        val textViewBody = view.findViewById<TextView>(R.id.textViewBody)

        textViewUserId.text = "User ID: ${item.userId}"
        textViewId.text = "ID: ${item.id}"
        textViewTitle.text = "Title:\n${item.title}"
        textViewBody.text = "Body:\n${item.body}"

        return view
    }
}