package com.app.backgroundservice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class BackgroundServiceMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.background_service_main_activity)

        val editTextTime = findViewById<EditText>(R.id.edit_text_time)
        val buttonStart = findViewById<Button>(R.id.button_start)

        buttonStart.setOnClickListener {
            val timeInSeconds = editTextTime.text.toString().toIntOrNull()
            if (timeInSeconds != null) {
                val intent = Intent(this, TimerService::class.java)
                intent.putExtra("timeInSeconds", timeInSeconds)
                startService(intent)
            }
        }
    }
}