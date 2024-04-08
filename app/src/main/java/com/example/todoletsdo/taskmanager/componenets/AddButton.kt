import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.todoletsdo.R

@Composable
@Preview(showBackground = true)
fun AddButton(){
    val context = LocalContext.current
    Row(
        modifier = Modifier.fillMaxSize().padding(end = 20.dp, bottom = 30.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        Image(
            painter = painterResource(id = R.drawable.add1),
            contentDescription = "For adding tasks",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .background(color = Color(0x94F9F905))
                .clickable {
                    Toast.makeText(context, "Wow this works ", Toast.LENGTH_LONG).show()
                }
        )
    }
}
