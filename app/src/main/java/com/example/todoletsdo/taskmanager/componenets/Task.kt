package com.example.todoletsdo.taskmanager.componenets
import android.content.Context
import com.google.common.reflect.TypeToken
import com.google.gson.Gson

// Step 1: Data class to represent a task
data class Task(
    val name: String,
    val description: String,
    val dueDate: String
)

object TaskManager {
    private const val TASKS_KEY = "tasks"

    // Function to save tasks to SharedPreferences
    fun saveTasks(context: Context, tasks: List<Task>) {
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
}

