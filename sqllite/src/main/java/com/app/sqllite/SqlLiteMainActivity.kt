package com.app.sqllite

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SqlLiteMainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: Adapter
    private lateinit var dbHelper: DatabaseHelper
    private val items = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sql_lite_main_activity)

        listView = findViewById(R.id.listView)
        val fab = findViewById<FloatingActionButton>(R.id.fab)

        dbHelper = DatabaseHelper(this)
        items.addAll(dbHelper.getAllData())
        adapter = Adapter(this, items)
        listView.adapter = adapter

        fab.setOnClickListener {
            val intent = Intent(this, AddDataActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        items.clear()
        items.addAll(dbHelper.getAllData())
        adapter.notifyDataSetChanged()
    }
}