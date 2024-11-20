package com.example.todoletsdo.taskmanager.componenets

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AlarmSetter(private val context: Context) {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("notes")
    private var alarmCounter = 0

    fun setAlarmFromDatabase() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            try {
                val canScheduleExactAlarmsMethod = AlarmManager::class.java.getMethod("canScheduleExactAlarms")
                val canScheduleExactAlarms = canScheduleExactAlarmsMethod.invoke(alarmManager) as Boolean
                if (!canScheduleExactAlarms) {
                    requestExactAlarmPermission()
                    return
                }
            } catch (e: Exception) {
                Log.e("AlarmSetter", "Error checking exact alarm permission: ${e.message}")
            }
        }

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (taskSnapshot in dataSnapshot.children) {
                    val startTimeString = taskSnapshot.child("startTime").getValue(String::class.java)
                    val dateString = taskSnapshot.child("date").getValue(String::class.java)

                    if (startTimeString != null && dateString != null) {
                        val alarmTimeInMillis = getAlarmTimeInMillis(startTimeString, dateString)
                        if (alarmTimeInMillis != null && alarmTimeInMillis > System.currentTimeMillis()) {
                            scheduleAlarm(alarmTimeInMillis)
                        } else {
                            Log.e("AlarmSetter", "Alarm time is in the past or invalid")
                        }
                    } else {
                        Log.e("AlarmSetter", "Alarm data missing in Firebase")
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("AlarmSetter", "Error reading alarm time: ${databaseError.message}")
            }
        })
    }

    private fun scheduleAlarm(alarmTimeInMillis: Long) {
        try {
            alarmCounter++
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                alarmCounter,
                alarmIntent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTimeInMillis, pendingIntent)
            } else {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeInMillis, pendingIntent)
            }

            Log.d("AlarmSetter", "Alarm set for: $alarmTimeInMillis")
        } catch (e: SecurityException) {
            Log.e("AlarmSetter", "Permission required to set exact alarms: ${e.message}")
            requestExactAlarmPermission()
        }
    }

    private fun getAlarmTimeInMillis(startTimeString: String, dateString: String): Long? {
        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        val dateFormat = SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault())

        return try {
            val startTime = timeFormat.parse(startTimeString)
            val date = dateFormat.parse(dateString)

            if (startTime != null && date != null) {
                val calendar = Calendar.getInstance()
                calendar.time = date
                calendar.set(Calendar.HOUR_OF_DAY, startTime.hours)
                calendar.set(Calendar.MINUTE, startTime.minutes)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)

                calendar.timeInMillis
            } else null
        } catch (e: Exception) {
            Log.e("AlarmSetter", "Error parsing time: ${e.message}")
            null
        }
    }

    private fun requestExactAlarmPermission() {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
        Toast.makeText(context, "Please allow exact alarm scheduling in settings", Toast.LENGTH_SHORT).show()
    }
}
