package com.example.todoletsdo.taskmanager.componenets

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // This method will be called when the alarm is triggered
        // You can perform any action here, such as showing a notification or starting a service
        // For demonstration purposes, let's display a toast message
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_SHORT).show()
    }
}