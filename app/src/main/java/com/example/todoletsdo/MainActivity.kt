package com.example.todoletsdo

import AddButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.example.todoletsdo.taskmanager.componenets.StartScreen
import com.example.todoletsdo.taskmanager.componenets.TaskComponent
import com.example.todoletsdo.taskmanager.componenets.WelcomeMessageComponent
import com.example.todoletsdo.taskmanager.components.ProfileHeaderComponent
import com.example.todoletsdo.ui.theme.ToDoLetsDoTheme
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.Random
import com.example.todoletsdo.taskmanager.componenets.AlarmSetter // Replace with your package name

// Data class for your tasks
data class Task(
    val id: Int = 0,
    val title: String = "",
    val body: String? = null,
    val startTime: String = "",
    val date: String = ""
)

class MainActivity : ComponentActivity(), LifecycleOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showStartScreen by remember { mutableStateOf(true) }
            var tasks by remember { mutableStateOf<List<Task>>(emptyList()) }
            val coroutineScope = rememberCoroutineScope()

            // Get a reference to the Firebase Realtime Database
            val database = Firebase.database
            val notesRef: DatabaseReference = database.getReference("notes")
            val alarmSetter = AlarmSetter(this)
            alarmSetter.setAlarmFromDatabase()
            // Fetch tasks from Firebase Realtime Database
            LaunchedEffect(Unit) {
                tasks = fetchTasks(notesRef)
            }

            LaunchedEffect(key1 = true) {
                kotlinx.coroutines.delay(4000) // Delay for 4 seconds
                showStartScreen = false
            }


            ToDoLetsDoTheme {
                if (showStartScreen) {
                    StartScreen() // Show splash screen
                } else {
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            contentPadding = PaddingValues(
                                start = 16.dp,
                                top = 16.dp,
                                bottom = 16.dp
                            )
                        ) {
                            item {
                                ProfileHeaderComponent() // Header
                            }
                            item {
                                Spacer(modifier = Modifier.height(23.dp))
                                WelcomeMessageComponent() // Welcome message
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                            items(tasks) { task ->
                                TaskComponent(task = task) // Show fetched tasks
                            }
                        }
                        AddButton(
                            onTaskAdded = { newTask ->
                                // Optional: Update local state if needed
                                tasks = tasks + newTask
                            },
                            modifier = Modifier.align(Alignment.BottomEnd)
                        )
                    }
                }
            }
        }
    }

    private suspend fun fetchTasks(notesRef: DatabaseReference): List<Task> {
        return try {
            val snapshot = notesRef.get().await()
            snapshot.children.mapNotNull { it.getValue(Task::class.java) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}

private suspend fun fetchTasks(notesRef: DatabaseReference): List<Task> {
    return try {
        val snapshot = notesRef.get().await()
        snapshot.children.mapNotNull { it.getValue(Task::class.java) }
    } catch (e: Exception) {
        emptyList()
    }
}


// Function to generate a unique ID for each task
fun generateUniqueId(): Int {
    val timestamp = System.currentTimeMillis()
    val random = Random()
    val randomId = random.nextInt(1000000) // Generate a random number between 0 and 999999
    return "$timestamp$randomId".hashCode() // Combine timestamp and random number and calculate hash code
}