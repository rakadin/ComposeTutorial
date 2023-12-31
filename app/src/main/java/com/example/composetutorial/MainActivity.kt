package com.example.composetutorial

import android.content.Intent
import android.content.res.Configuration
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                ) {
                    Conversation(messages = SampleData.conversationSample, navController = NavController(this))
                }
            }
        }
    }
}

data class Message(val author : String, val body:String)
@Composable
fun Conversation(messages: List<Message>,navController: NavController) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message, navController )
        }
    }
}
@Composable
fun MessageCard(msg : Message,navController : NavController){
    val mcontext = LocalContext.current
    Row(modifier = Modifier
        .padding(all = 8.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.tree_cloud_icon),
            contentDescription = "message avatar",
            modifier = Modifier
                .padding(all = 4.dp)
                .size(40.dp)// image size in dp //
                .clip(CircleShape) // reshape into circle
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by rememberSaveable { mutableStateOf(false) }
        val extraPadding by animateDpAsState( if(isExpanded) 30.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ))
        // Define an animated color state
        val targetColor = if (isExpanded) Color.Red else Color.Green
        val animatedColor by animateColorAsState(
            targetValue = targetColor,
            animationSpec = tween(
                durationMillis = 1000, // Animation duration
                easing = LinearOutSlowInEasing // Easing function
            )
        )
        // when click the card, change isExpanded values
        Column (modifier = Modifier.clickable {}
        ) {
            Text(text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(6.dp))

            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 2.dp) {
                Text(text = msg.body,
                    modifier = Modifier.padding(bottom = extraPadding.coerceAtLeast(0.dp)),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium)
            }

        }
        OutlinedButton(onClick = { isExpanded = !isExpanded },
            modifier = Modifier.padding(all = 6.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = animatedColor // set text color for button)
                )
        ) {
            Text(if (isExpanded) "Less" else "More")
        }
        ElevatedButton(onClick =
        {
            Log.d("Activity", "activity 2")
            val intent = Intent(mcontext,MainActivity2::class.java)
            intent.putExtra("value1",msg.author+" send " +msg.body)
            mcontext.startActivity(intent) },
            modifier = Modifier.padding(all = 6.dp)) {
            Text("Share")
        }

    }

}

/**
 * SampleData for Jetpack Compose Tutorial
 */
object SampleData {
    // Sample conversation data
    val conversationSample = listOf(
        Message(
            "Lexi",
            "Test...Test...Test..."
        ),
        Message(
            "Lexi",
            """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
        ),
        Message(
            "Lexi",
            """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
        ),
        Message(
            "Lexi",
            "Searching for alternatives to XML layouts..."
        ),
        Message(
            "Lexi",
            """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
        ),
        Message(
            "Lexi",
            "It's available from API 21+ :)"
        ),
        Message(
            "Lexi",
            "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
        ),
        Message(
            "Lexi",
            "Android Studio next version's name is Arctic Fox"
        ),
        Message(
            "Lexi",
            "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
        ),
        Message(
            "Lexi",
            "I didn't know you can now run the emulator directly from Android Studio"
        ),
        Message(
            "Lexi",
            "Compose Previews are great to check quickly how a composable layout looks like"
        ),
        Message(
            "Lexi",
            "Previews are also interactive after enabling the experimental setting"
        ),
        Message(
            "Lexi",
            "Have you tried writing build.gradle with KTS?"
        ),
    )
}
