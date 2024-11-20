package com.example.todoletsdo.taskmanager.componenets

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // This method will be called when the alarm is triggered
        Log.d("AlarmReceiver", "Alarm triggered successfully!")
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_SHORT).show()

        // Additional actions can be added here, such as sending a notification
    }
}
