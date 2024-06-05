package com.app.backgroundservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import androidx.core.app.NotificationCompat

class TimerService : Service() {

    private var countdownTimer: CountDownTimer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val timeInSeconds = intent?.getIntExtra("timeInSeconds", 0) ?: 0
        startCountdown(timeInSeconds)
        return START_STICKY
    }

    private fun startCountdown(timeInSeconds: Int) {
        countdownTimer?.cancel()

        countdownTimer = object : CountDownTimer((timeInSeconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                showNotification()
                stopSelf()
            }
        }.start()
    }

    private fun showNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "countdown_channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Countdown Timer", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Time's up!")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(1, notification)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        countdownTimer?.cancel()
        super.onDestroy()
    }
}