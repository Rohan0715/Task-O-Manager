package com.example.todoletsdo.taskmanager.componenets

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoletsdo.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground=true)
@Composable
fun ProfileHeaderComponent() {
    val context = LocalContext.current
    var showDialogBox by remember { mutableStateOf(false) }
    var selectedImageIndex by remember { mutableIntStateOf(0) }
    var showToast by remember { mutableStateOf(false) }
    var confirmation by remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(
                id = when (selectedImageIndex) {
                    0 -> R.drawable.profilefinal
                    1 -> R.drawable.owl
                    3 -> R.drawable.app_icon
                    4 -> R.drawable.image0_0
                    else -> R.drawable.owl
                }
            ),
            contentDescription = "Selected Image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .clickable {
                    showDialogBox = true
                }
        )

        if (showDialogBox) {
            AlertDialog(
                onDismissRequest = { showDialogBox = true },
                confirmButton = { showDialogBox = true },
                text = {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start=16.dp,end=16.dp) // Add padding to the column
                        ) {
                                    Image(
                                        painter = painterResource(id = (R.drawable.app_icon)),
                                        contentDescription = "Profile pic",
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape)
                                            .clickable {
                                                selectedImageIndex = 3
                                                showToast = true
                                            }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))

                                    Image(
                                        painter = painterResource(id = R.drawable.owl),
                                        contentDescription = "ProfilePic",
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(CircleShape)
                                            .clickable {
                                                selectedImageIndex = 1
                                                showToast = true
                                            }

                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Image(
                                        painter = painterResource(id = (R.drawable.image0_0)),
                                        contentDescription = "Profile pic",
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape)
                                            .clickable {
                                                selectedImageIndex = 4
                                                showToast = true
                                            }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))

                                    Image(
                                        painter = painterResource(id = R.drawable.profilefinal),
                                        contentDescription = "ProfilePic",
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(CircleShape)
                                            .clickable {
                                                selectedImageIndex = 0
                                                showToast = true
                                            }
                                    )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically, // Center the content vertically
                            modifier = Modifier.fillMaxWidth(), // Fill the width of the parent
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = { showDialogBox = false },
                                colors = ButtonDefaults.buttonColors(Color(0xFF000000))

                            ) {
                                Text(text = "Cancel")
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Button(
                                onClick = {
                                    showDialogBox = false
                                    confirmation = true
                                }, colors = ButtonDefaults.buttonColors(Color(0xFF000000))
                            ) {
                                Text(text = "Done")
                            }
                        }
                    }
                }
            )
        }
        if (confirmation && showToast) {
            Toast.makeText(context, "Your profile pic is Updated!!", Toast.LENGTH_SHORT).show()
        }
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Hi Rohan!!", fontSize = 22.sp,
                fontFamily = FontFamily(Font(R.font.nunitoextrabold)),
                modifier = Modifier
                    .weight(1f)
                    //**ABOUT WEIGHT ATTRIBUTE--When you use Modifier.weight(1f) in a Row with a single child, it will take up all the available space along the main axis, regardless of whether you set it to 1f or 10f, because there are no other elements competing for space. The weight is relative, and since it’s the only element with a weight, it gets all the space.
                    //
                    //If you had multiple elements with different weights, then the space would be distributed proportionally. For example, if one element had Modifier.weight(1f) and another had Modifier.weight(2f), the second element would take up twice as much space as the first.
                    .padding(start = 5.dp)
                    .clickable {
                        Toast
                            .makeText(
                                context,
                                "This could only be changed once you have changed your account ",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
            )


            BadgedBox(
                badge = {
                    Badge(
                        modifier = Modifier
                            .size(12.dp)
                            .offset(y = 8.dp, x = (-4).dp), contentColor = Color.White
                    )

                },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            showDialog.value = true
                        }
                )

                if (showDialog.value) {
                    // Pass the showDialog state to the NotificationDialog
                    notifie.NotificationDialog(showDialog = showDialog)
                }
            }
        }
    }
}

