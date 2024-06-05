package com.app.restapi

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApiMainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: Adapter
    private val items = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rest_api_main_activity)

        listView = findViewById(R.id.listView)
        adapter = Adapter(this, items)
        listView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        service.getData().enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(call: Call<List<DataModel>>, response: Response<List<DataModel>>) {
                response.body()?.let { items.addAll(it) }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<DataModel>>, t: Throwable) {
                Toast.makeText(this@RestApiMainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}