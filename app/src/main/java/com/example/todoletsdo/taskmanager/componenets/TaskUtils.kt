package com.example.todoletsdo.taskmanager.componenets




import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.example.todoletsdo.ui.theme.Task
object TaskUtils {
    private const val TASKS_KEY = "tasks"

    // Function to save tasks to SharedPreferences
    fun saveTasks(context: Context, tasks: List<Any>) {
        val json = Gson().toJson(tasks)
        val sharedPreferences = context.getSharedPreferences("task_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(TASKS_KEY, json)
        editor.apply()
    }

    // Function to retrieve tasks from SharedPreferences
    fun getTasks(context: Context): List<Task> {
        val sharedPreferences = context.getSharedPreferences("task_prefs", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(TASKS_KEY, null)
        val type = object : TypeToken<List<Task>>() {}.type
        return Gson().fromJson(json, type) ?: emptyList()
    }

    object AlarmUtils {
        fun setAlarm(context: Context, startTimeMillis: Long, taskId: Int) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, AlarmReceiver::class.java)
            // Add any necessary data to the intent
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                taskId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, startTimeMillis, pendingIntent)
        }

    }
}

