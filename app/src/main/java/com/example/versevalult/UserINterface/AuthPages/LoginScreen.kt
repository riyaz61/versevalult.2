package com.example.versevalult.UserINterface.AuthPages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.versevalult.R





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val backgroundColor = Color.White
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        color = Color.Transparent
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Full-width Image
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "Login art image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Title
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Sign in to your Account",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Email TextField
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = Color.Gray) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password TextField
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password", color = Color.Gray) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(color = Color.Black),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Login Button
                Button(
                    onClick = {
                        // Validate inputs
                        if (email.isBlank() || password.isBlank()) {
                            Toast.makeText(context, "Email and Password cannot be empty.", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            Toast.makeText(context, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        isLoading = true // Show loader
                        loginUser(email, password) { result ->
                            isLoading = false // Hide loader
                            when (result) {
                                "success" -> {
                                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                                    navController.navigate("Main") // Navigate to main page
                                }
                                "invalid_credentials" -> {
                                    Toast.makeText(context, "Invalid email or password.", Toast.LENGTH_LONG).show()
                                }
                                "user_disabled" -> {
                                    Toast.makeText(context, "This account has been disabled.", Toast.LENGTH_LONG).show()
                                }
                                else -> {
                                    Toast.makeText(context, "Error: $result", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853))
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp)
                    } else {
                        Text(text = "Login", color = Color.White, fontSize = 16.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Registration Prompt
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Don't have an account?", color = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Click here",
                        color = Color(0xFF00C853),
                        modifier = Modifier.clickable { navController.navigate("Register") }
                    )
                }
            }
        }
    }
}




