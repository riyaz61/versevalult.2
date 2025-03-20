package com.example.versevalult.UserINterface.Onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.versevalult.R
import com.example.versevalult.ui.theme.customFontFamily


@Composable
fun OnboardingScreen3(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp), // Bottom padding for the Row
        verticalArrangement = Arrangement.SpaceBetween, // Space elements between top and bottom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp)) // Top spacer for the image

        Image(
            painter = painterResource(id = R.drawable.onboard3),
            contentDescription = "Login image",
            modifier = Modifier.size(300.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Optional padding for better text alignment
        ) {
            Text(
                text = "Let's get your journey started",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontFamily = customFontFamily,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontFamily = customFontFamily,
                color = Color.Black
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(20.dp)
        ) {
            Button(
                onClick = { navController.navigate("Login") },
                modifier = Modifier
                    .width(550.dp), // Adjust width as needed
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA7DF73) // Light green color
                ),
                shape = RoundedCornerShape(12) // Rounded edges
            ) {
                Text(
                    text = "Login",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(10.dp)) // Add 20 dp gap between buttons

            Button(
                onClick = { navController.navigate("Register") },
                modifier = Modifier
                    .width(550.dp).border(2.dp, Color.Black).border(1.dp, Color.Black), // Adjust width as needed
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White // Adjust color as needed
                ),
                shape = RoundedCornerShape(12) // Rounded edges
            ) {
                Text(
                    text = "Sign up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}