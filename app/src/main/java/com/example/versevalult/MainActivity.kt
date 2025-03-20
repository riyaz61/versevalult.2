package com.example.versevalult

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.versevalult.InnerPages.PoemDetailsScreen
import com.example.versevalult.InnerPages.PoemsGridScreen
import com.example.versevalult.UserINterface.AuthPages.LoginScreen
import com.example.versevalult.UserINterface.AuthPages.RegisterScreen
import com.example.versevalult.UserINterface.DataClass.Poem
import com.example.versevalult.UserINterface.DataClass.Poet
import com.example.versevalult.UserINterface.MainPages.MainScreen
import com.example.versevalult.UserINterface.Onboard.OnboardingScreen1
import com.example.versevalult.UserINterface.Onboard.OnboardingScreen2
import com.example.versevalult.UserINterface.Onboard.OnboardingScreen3
import com.example.versevalult.ui.theme.VerseValultTheme
import com.google.gson.Gson
import java.net.URLDecoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerseValultTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    // A dark gray color often used in dark themes
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
                        composable("Login") {
                            LoginScreen(navController = navController)
                        }
                        composable("Register") {
                            RegisterScreen(navController = navController)
                        }
                        composable("Main") {
                            MainScreen(navController = navController)
                        }
                        composable(
                            "Poems/{poems}",
                            arguments = listOf(navArgument("poems") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val jsonPoems = backStackEntry.arguments?.getString("poems") ?: ""

                            val gson = Gson()
                            val poems: List<Poem> = if (jsonPoems.isNotEmpty()) {
                                try {
                                    val decodedJson = URLDecoder.decode(jsonPoems, "UTF-8") // Decode the JSON string
                                    gson.fromJson(decodedJson, Array<Poem>::class.java).toList()
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                    emptyList() // Return an empty list in case of an error
                                }
                            } else {
                                emptyList()
                            }

                            // Pass the deserialized poems to the next screen
                            PoemsGridScreen(navController = navController, poems)
                        }


                        composable(
                            route = "Poem_Details/{lines}/{title}",
                            arguments = listOf(
                                navArgument("lines") { type = NavType.StringType },
                                navArgument("title") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val linesJson = backStackEntry.arguments?.getString("lines") ?: ""
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            val lines = Gson().fromJson(linesJson, Array<String>::class.java).toList()

                            PoemDetailsScreen(navController = navController, lines = lines, title = title)
                        }



//                        composable(
//                            "Poems/{poems}",
//                            arguments = listOf(navArgument("poems") { type = NavType.StringType })
//                        ) { backStackEntry ->
//                            val jsonPoems = backStackEntry.arguments?.getString("poems") ?: ""
//                            val gson = Gson()
//                            val poems: List<Poem> = gson.fromJson(jsonPoems, Array<Poem>::class.java).toList()
//
//                            PoemsGridScreen(navController = navController, poems = poems)
//                        }


                    })
                }
            }
        }
    }
}

