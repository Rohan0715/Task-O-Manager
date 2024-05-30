package com.example.todoletsdo.taskmanager.componenets

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoletsdo.R


object notifie {

    @Composable
    fun NotificationDialog(showDialog: MutableState<Boolean>) {
        val context=LocalContext.current
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = {
                Text("Notification", modifier = Modifier.padding(start = 1.dp),
                        fontFamily = FontFamily(Font(R.font.nunitobold))
                )
            },
            text = {
                Text(
                    "No notifications for now!!", fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.nunitolight)),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog.value = false },
                    colors = ButtonDefaults.buttonColors(Color(0xFF000000))
                    , shape= CircleShape
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value=false
                    Toast.makeText(context, "this is an confirmation button that it does work ", Toast.LENGTH_SHORT).show() },
                    modifier=Modifier.padding(start = 1.dp),
                    colors=ButtonDefaults.buttonColors(Color(0xFF000000))
                ) {
                    Text(text = "Confirm")
                }

            }
        )
    }
}