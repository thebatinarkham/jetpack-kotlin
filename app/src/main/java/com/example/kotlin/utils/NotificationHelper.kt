package com.example.kotlin.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.kotlin.MainActivity
import com.example.kotlin.R
import com.example.kotlin.model.ToDoData

class NotificationHelper(val context: Context,val toDoData: ToDoData,val message:String){
    private val CHANNEL_ID = "TodoList Channel Id"
    private val NOTIFICATION_ID= 123

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = CHANNEL_ID
            val descriptionText = "Channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptionText
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(){
        createNotificationChannel()
        val intent = Intent(context,MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context,0,intent,0)
        val icon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_baseline_leak_add_24)

        val notification = NotificationCompat.Builder(context,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_leak_add_24)
            .setLargeIcon(icon)
            .setContentTitle("Todo:")
            .setContentText("${message}: ${toDoData.title}")
            .setStyle(NotificationCompat.BigPictureStyle().bigLargeIcon(icon).bigLargeIcon(null))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID,notification)

    }
}