package com.example.todoletsdo
import AddButton
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import com.example.todoletsdo.taskmanager.componenets.StartScreen
import com.example.todoletsdo.ui.theme.ToDoLetsDoTheme
import com.example.todoletsdo.taskmanager.componenets.ProfileHeaderComponent
import com.example.todoletsdo.taskmanager.componenets.TaskComponent
import com.example.todoletsdo.taskmanager.componenets.WelcomeMessageComponent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoletsdo.taskmanager.componenets.TaskUtils
import com.example.todoletsdo.ui.theme.Task
import com.example.todoletsdo.ui.theme.taskList


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showStartScreen by remember { mutableStateOf(true)}
            var tasks by remember { mutableStateOf(emptyList<Task>())}
            tasks = TaskUtils.getTasks(this@MainActivity)
            // Used LaunchedEffect to introduce a delay
            LaunchedEffect(key1 = true) {
                delay(4000) // Delay for 4 seconds
                showStartScreen = false

            }

            ToDoLetsDoTheme {
                if (showStartScreen) {
                    // Show the StartScreen composable for 5 seconds
                    StartScreen()
                } else {
                    // After 4 seconds, show the main interface
                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn(
                            contentPadding = PaddingValues(start = 16.dp, top = 16.dp,  bottom = 16.dp)
                        ) {
                            item {
                                ProfileHeaderComponent()
                            }
                            item {
                                Spacer(modifier = Modifier.height(23.dp))
                                WelcomeMessageComponent()
                                Spacer(modifier = Modifier.height(20.dp))
                            }
//                            items(tasks) { task ->
//                                TaskComponent(task = task)
//                            }
                            items(taskList) { task ->
                                TaskComponent(task = task)
                            }
                        }
                        AddButton()
                    }
                }
            }
        }

    }
}
