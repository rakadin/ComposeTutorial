package com.example.composetutorial.xml_activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.ComposeView
import com.example.composetutorial.Greeting2
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import com.example.composetutorial.R

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                setContentView(R.layout.activity_login)

                val composeView = findViewById<ComposeView>(R.id.composeview) // Corrected ID
                composeView.setContent {
                    Greeting2(name = " From Compose -> XML")
                }
            }
        }
    }
}
