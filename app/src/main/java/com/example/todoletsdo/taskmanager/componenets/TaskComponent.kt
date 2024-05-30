package com.example.todoletsdo.taskmanager.componenets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoletsdo.R
import com.example.todoletsdo.ui.theme.Colorse.Cyan80
import com.example.todoletsdo.ui.theme.Colorse.LightOrange
import com.example.todoletsdo.ui.theme.Colorse.Orange
import com.example.todoletsdo.ui.theme.Colorse.Pink80
import com.example.todoletsdo.ui.theme.Colorse.Purple80
import com.example.todoletsdo.ui.theme.Colorse.PurpleGrey80
import com.example.todoletsdo.ui.theme.Colorse.teal200
import com.example.todoletsdo.ui.theme.Task

@Composable

fun TaskComponent( task: Task) {
//Box(modifier = Modifier.fillMaxSize().background(Color(0x70F9F905)))
    val taskColor= listOf(Purple80, PurpleGrey80,Pink80, Orange, teal200,
        LightOrange, Cyan80).random()
    Column(modifier=Modifier.fillMaxSize()
        ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 6.dp, bottom = 20.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(30.dp)
            // because of these two lines the two componenelt wasa perfrectlly alligned as i have removed it i know these component will be in differnt order

        )
        {
            Text(
                text = task.startTime,
                fontFamily = FontFamily(Font(R.font.nunitobold)),
                textAlign = TextAlign.Center
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(
                            border = BorderStroke(5.dp, Color.Black),
                            shape = CircleShape
                        )

                )
                Divider(modifier = Modifier.size(3.dp), color = Color.Black)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .width(700.dp).wrapContentHeight()
                            .clip(RoundedCornerShape(14.dp))
                            .background(taskColor)
                            //ABOUT COLOR CODES (TRANSPARENCEY)
//                             Now, if we want to use this color in our code with different levels of transparency, we can add an alpha value at the start:
//                     Fully opaque (no transparency): If we want the color to be fully opaque, we add FF at the start. So, the color code becomes 0xFFFF5733.
//                     50% transparent: If we want the color to be semi-transparent or 50% transparent, we can add 80 at the start. So, the color code becomes 0x80FF5733.
//                     Fully transparent: If we want the color to be fully transparent, we add 00 at the start. So, the color code becomes 0x00FF5733. But, of course, a fully transparent color won’t be visible.
                            .weight(0.9f), verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = task.title,
                            fontFamily = FontFamily(Font(R.font.nunitobold)),
                            modifier = Modifier
                                .width(700.dp).wrapContentHeight()
                                .padding(
                                    top = 12.dp,
                                    start = 20.dp
                                )
                                .size(20.dp), softWrap = true
                        )
                        if (task.body != null) {
                            //if the body founded to be empty then it will show an error cause in task we
                            //defined val body:String? =null that means if the string is not passed then itwill be setted as null
                            //simply if i will say that the variable body can be nullable
                            Text(
                                text = task.body,
                                fontFamily = FontFamily(Font(R.font.nunitolight)),
                                modifier = Modifier
                                    .padding(
                                        start = 20.dp
                                    )
                                    .width(700.dp)
                                    .wrapContentHeight()//here wrapContentHeight if for approving the text that it could use the heigh accordingly
                                ,
                                softWrap = true//whereas softwrap makes the text comex to the next line without this the text overflows to the max width and goes beyond the widht this makes the reaming text come to the next line
                                ,
                                color = Color.Black
                            )
                        }

                        Text(
                            text = task.date,
                            fontFamily = FontFamily(Font(R.font.nunitobold)),
                            modifier = Modifier
                                .width(600.dp).wrapContentHeight()
                                .padding(
                                    start = 20.dp, bottom = 12.dp
                                )
                                .size(20.dp),
                            color = Color.Black,
                            softWrap = true//whereas softwrap makes the text comex to the next line without this the text overflows to the max width and goes beyond the widht this makes the reaming text come to the next line
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .size(3.dp)
                            .weight(0.1f), color = Color.Black
                    )
                }
            }
        }
    }
}
