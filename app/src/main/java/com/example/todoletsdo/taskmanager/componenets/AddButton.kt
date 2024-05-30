import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoletsdo.R
import com.example.todoletsdo.taskmanager.componenets.DateTimePicker
import com.example.todoletsdo.taskmanager.componenets.TaskUtils
import com.example.todoletsdo.ui.theme.Task
import java.util.Random

@Composable
@Preview(showBackground = true)
fun AddButton(){
    val context = LocalContext.current
    var showDialog by remember {mutableStateOf(false)}
    // Declare variables outside the AlertDialog block
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<Task>() }
    // Line of code for managing the database
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 20.dp, bottom = 30.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "For adding tasks",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(color = Color.White)
                .clickable {
                    showDialog = true
                }
        )
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
                    Row(verticalAlignment = Alignment.CenterVertically){
                        Button(onClick = {
                            // Show the Date and Time Picker when the button is clicked
                            DateTimePicker.showDateTimePicker(context) { dateTime ->
                                // Split the selected date and time into separate variables
                                selectedDate = dateTime.split(" at ")[0]
                                selectedTime = dateTime.split(" at ")[1]
                            }
                        }, colors = ButtonDefaults.buttonColors(Color(0xFFFF5722))) {
                            Text("Select Date and Time")
                        }
                        if (selectedDate.isNotEmpty() && selectedTime.isNotEmpty()) {
                            Text(" Selected Date:$selectedDate\n Selected Time:$selectedTime")
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
                        TaskUtils.saveTasks(context, TaskUtils.getTasks(context) + newTask)
                        showDialog=false
                        Toast.makeText(context, "The Tasks been added!!", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFF000000))
                ) {
                    Text("Save Task")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false },
                    colors=ButtonDefaults.buttonColors(Color(0xFF000000))
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}

// Define the Task data class here

fun generateUniqueId() : Int{
    val timestamp = System.currentTimeMillis()
    val random = Random()
    val randomId = random.nextInt(1000000) // Generate a random number between 0 and 999999
    return "$timestamp$randomId".hashCode() // Combine timestamp and random number and calculate hash code
}




