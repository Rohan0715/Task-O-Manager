package com.example.todoletsdo.taskmanager.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todoletsdo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileHeaderComponent() {
    val context = LocalContext.current
    var showDialogBox by remember { mutableStateOf(false) }
    var showNotificationDialog by remember { mutableStateOf(false) }
    var selectedImageIndex by remember { mutableIntStateOf(0) }
    var notificationCount by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.background,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Animated Profile Image
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Image(
                    painter = painterResource(
                        id = when (selectedImageIndex) {
                            0 -> R.drawable.profilefinal
                            1 -> R.drawable.owl
                            3 -> R.drawable.app_icon
                            else -> R.drawable.owl
                        }
                    ),
                    contentDescription = "Selected Profile Image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { showDialogBox = true }
                        .scale(0.9f)
                )
            }

            // Greeting and Actions
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column {
                    Text(
                        text = "Hi, Rohan!",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Text(
                        text = "Welcome back",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }

                // Notifications with Interactive Badge
                BadgedBox(
                    badge = {
                        if (notificationCount > 0) {
                            Badge(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = Color.White
                            ) {
                                Text(notificationCount.toString())
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Notifications,
                        contentDescription = "Notifications",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                showNotificationDialog = true
                            },
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                // Settings Icon
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            Toast.makeText(
                                context,
                                "Settings coming soon!",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

    // Profile Picture Selection Dialog
    if (showDialogBox) {
        ProfilePictureSelectionDialog(
            onSelectImage = { index ->
                selectedImageIndex = index
                Toast.makeText(context, "Profile picture updated!", Toast.LENGTH_SHORT).show()
                showDialogBox = false
            },
            onCancel = { showDialogBox = false }
        )
    }

    // Notification Dialog
    if (showNotificationDialog) {
        AlertDialog(
            onDismissRequest = {
                showNotificationDialog = false
            },
            title = {
                Text(
                    "Notifications",
                    style = MaterialTheme.typography.titleMedium
                )
            },
            text = {
                Text(
                    "You have no new notifications at the moment.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showNotificationDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
fun ProfilePictureSelectionDialog(
    onSelectImage: (Int) -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        title = {
            Text("Choose Profile Picture", style = MaterialTheme.typography.titleMedium)
        },
        text = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfilePictureOption(
                    imageRes = R.drawable.profilefinal,
                    onSelectImage = onSelectImage,
                    imageIndex = 0
                )
                ProfilePictureOption(
                    imageRes = R.drawable.owl,
                    onSelectImage = onSelectImage,
                    imageIndex = 1
                )
                ProfilePictureOption(
                    imageRes = R.drawable.app_icon,
                    onSelectImage = onSelectImage,
                    imageIndex = 3
                )
            }
        },
        confirmButton = {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun ProfilePictureOption(
    imageRes: Int,
    onSelectImage: (Int) -> Unit,
    imageIndex: Int
) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Profile Picture Option",
        modifier = Modifier
            .size(70.dp)
            .clip(CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onSelectImage(imageIndex) }
    )
}
