package com.example.versevalult.UserINterface.Onboard

import androidx.compose.foundation.Image
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
fun OnboardingScreen2(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp), // Bottom padding for the Row
        verticalArrangement = Arrangement.SpaceBetween, // Space elements between top and bottom
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp)) // Top spacer for the image

        Image(
            painter = painterResource(id = R.drawable.onboard2),
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
                text = "Find poems according to your mood",
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

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {

            Button(
                onClick = { navController.navigate("onboard2") },
                modifier = Modifier.width(150.dp).height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),

            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            Button(
                onClick = { navController.navigate("onboard3") },
                modifier = Modifier.width(100.dp).height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFA7DF73)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}