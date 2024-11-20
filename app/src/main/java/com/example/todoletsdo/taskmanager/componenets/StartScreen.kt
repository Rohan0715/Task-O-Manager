package com.example.todoletsdo.taskmanager.componenets
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition // required liaray for animation to run
import com.example.todoletsdo.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showBackground = true)
fun StartScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.monkey))
    val composition1 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.load))
//    In Kotlin, the by keyword is used for delegation, which is a design pattern where an object hands over its responsibilities to another object. When you use by in
//    the context of rememberLottieComposition, you’re telling the compiler that the composition1 property should be delegated to whatever rememberLottieComposition returns.
//    On the other hand, = is used for direct assignment in Kotlin. If you were to use = instead of by, you would be assigning the result of
//    rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.load)) directly to composition1, without the benefits of delegation.
//    Delegation can be particularly useful for lazy initialization, thread safety, or to encapsulate the logic of property access
//    (getting or setting) within a delegate object. In the case of rememberLottieComposition, it’s likely that the function provides some additional logic to handle the Lottie composition lifecycle
//    within a Jetpack Compose UI, which you benefit from by using by
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(300.dp),
            composition = composition//composition means the file like an res= file
        )
        LottieAnimation(
            modifier = Modifier.height(75.dp),
            composition = composition1
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "WELCOME Z'SS",
            fontFamily = FontFamily(Font(R.font.nunitolight)),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
