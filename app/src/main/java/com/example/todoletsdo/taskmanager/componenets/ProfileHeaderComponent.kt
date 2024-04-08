package com.example.todoletsdo.taskmanager.componenets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoletsdo.R
import java.time.temporal.TemporalQueries.offset

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground=true)
@Composable
fun ProfileHeaderComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profilefinal),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Row(modifier=Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
             Text(text = "Hi Rohan!!"
                 ,fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.nunitoextrabold)),
                 modifier= Modifier.weight(1f)
                     //**ABOUT WEIGHT ATTRIBUTE--When you use Modifier.weight(1f) in a Row with a single child, it will take up all the available space along the main axis, regardless of whether you set it to 1f or 10f, because there are no other elements competing for space. The weight is relative, and since it’s the only element with a weight, it gets all the space.
                     //
                     //If you had multiple elements with different weights, then the space would be distributed proportionally. For example, if one element had Modifier.weight(1f) and another had Modifier.weight(2f), the second element would take up twice as much space as the first.
                    .padding(start = 5.dp))
                 

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
                    Modifier.size(30.dp)
                )
            }
        }
    }
}