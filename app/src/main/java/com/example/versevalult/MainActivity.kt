package com.example.versevalult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.versevalult.UserINterface.Onboard.OnboardingScreen1
import com.example.versevalult.UserINterface.Onboard.OnboardingScreen2
import com.example.versevalult.UserINterface.Onboard.OnboardingScreen3
import com.example.versevalult.ui.theme.VerseValultTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerseValultTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF121212) // A dark gray color often used in dark themes
                ){
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Onboard1" , builder = {
                        composable("Onboard1") { 
                            OnboardingScreen1(navController = navController)
                        }
                        composable("onboard2") { 
                            OnboardingScreen2(navController = navController)
                        }
                        composable("onboard3") {  
                            OnboardingScreen3(navController = navController)
                        }
                    })
                }
            }
        }
    }
}

