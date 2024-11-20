import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.todoletsdo.Task
import com.example.todoletsdo.generateUniqueId
import com.example.todoletsdo.taskmanager.componenets.DateTimePicker
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

@Composable
fun AddButton(
    onTaskAdded: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    // Get a reference to the Firebase Realtime Database
    val database = Firebase.database
    val notesRef: DatabaseReference = database.getReference("notes")

    FloatingActionButton(
        onClick = { showDialog = true },
        modifier = modifier.padding(16.dp)
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Task")
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = {
                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    TextField(
                        value = body,
                        onValueChange = { body = it },
                        label = { Text("Body") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Button(
                            onClick = {
                                DateTimePicker.showDateTimePicker(context) { dateTime ->
                                    selectedDate = dateTime.split(" at ")[0]
                                    selectedTime = dateTime.split(" at ")[1]
                                }
                            },
                            colors = ButtonDefaults.buttonColors(Color(0xFFFF5722))
                        ) {
                            Text("Select Date and Time")
                        }
                        if (selectedDate.isNotEmpty() && selectedTime.isNotEmpty()) {
                            Text(" Selected: $selectedDate at $selectedTime")
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val taskId = generateUniqueId()
                        val newTask = Task(
                            id = taskId,
                            title = title,
                            body = body,
                            startTime = selectedTime,
                            date = selectedDate
                        )
                        notesRef.child(taskId.toString()).setValue(newTask)
                            .addOnSuccessListener {
                                Toast.makeText(context, "Task added successfully!", Toast.LENGTH_SHORT).show()
                                onTaskAdded(newTask)
                                showDialog = false
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "Error adding task", Toast.LENGTH_SHORT).show()
                            }
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF000000))
                ) {
                    Text("Save Task")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(Color(0xFF000000))
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}