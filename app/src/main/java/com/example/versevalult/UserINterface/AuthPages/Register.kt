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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.versevalult.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    val backgroundColor = Color.White
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // Loader state
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        color = Color.Transparent,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color(0xFF00C853))
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 20.dp, 0.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.login),
                        contentDescription = "Background Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Register",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        // Full Name TextField
                        OutlinedTextField(
                            value = fullName,
                            onValueChange = { fullName = it },
                            label = { Text("Full Name", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = Color.Black)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Email TextField
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            textStyle = TextStyle(color = Color.Black)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Password TextField
                        var passwordVisible by remember { mutableStateOf(false) }
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            textStyle = TextStyle(color = Color.Black)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Repeat Password TextField
                        OutlinedTextField(
                            value = repeatPassword,
                            onValueChange = { repeatPassword = it },
                            label = { Text("Repeat Password", color = Color.Gray) },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            textStyle = TextStyle(color = Color.Black)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Register Button
                        Button(
                            onClick = {
                                if (password == repeatPassword) {
                                    isLoading = true
                                    registerUser(
                                        fullName,
                                        email,
                                        password,
                                        location = ""
                                    ) { result ->
                                        isLoading = false
                                        if (result == "Success") {
                                            navController.navigate("Login")
                                            Toast.makeText(context, "Registration Successful!", Toast.LENGTH_LONG).show()

                                        } else {
                                            Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853)),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = "Register", color = Color.White, fontSize = 16.sp)
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Login Text
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "I have an account?", color = Color.Gray)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Log in",
                                color = Color(0xFF00C853),
                                modifier = Modifier.clickable { navController.navigate("Login") }
                            )
                        }
                    }
                }
            }
        }
    }
}
