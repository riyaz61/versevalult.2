package com.example.versevalult.UserINterface.MainPages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.OffsetMapping
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.versevalult.R
import com.example.versevalult.UserINterface.DataClass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun EditPage(navController: NavHostController) {
    val context = LocalContext.current
    val db = FirebaseDatabase.getInstance().reference

    // State variables
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var country by remember { mutableStateOf(TextFieldValue("")) }
    var isLoading by remember { mutableStateOf(true) } // Loader state

    // Fetch user data from Firebase
    LaunchedEffect(Unit) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            db.child("users").child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    try {
                        val user = snapshot.getValue(User::class.java)
                        if (user != null) {
                            name = TextFieldValue(user.name)
                            email = TextFieldValue(user.email)
                            country = TextFieldValue(user.location)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(context, "Error parsing data: ${e.message}", Toast.LENGTH_SHORT).show()
                    } finally {
                        isLoading = false // Hide loader after fetching data
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Error fetching data: ${error.message}", Toast.LENGTH_SHORT).show()
                    isLoading = false // Hide loader even on error
                }
            })
        } else {
            Toast.makeText(context, "User not logged in.", Toast.LENGTH_SHORT).show()
            isLoading = false
        }
    }

    // Function to save user data
    fun saveUserData() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val updatedUser = User(
                name = name.text,
                email = email.text,
                location = country.text
            )
            db.child("users").child(userId).setValue(updatedUser)
                .addOnSuccessListener {
                    Toast.makeText(context, "Changes saved successfully", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error saving data: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(context, "User not logged in.", Toast.LENGTH_SHORT).show()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.background
    ) {
        if (isLoading) {
            // Loader UI
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator() // Loader component
            }
        } else {
            // Main UI
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Image placeholder
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Open gallery to select an image",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.login), // Replace with actual placeholder
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(100.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Editable fields
                InputField(label = "Name", value = name, onValueChange = { name = it })
                InputField(label = "Email Address", value = email, onValueChange = { email = it })
                InputField(label = "Country", value = country, onValueChange = { country = it })

                Spacer(modifier = Modifier.height(32.dp))

                // Save Changes Button
                Button(
                    onClick = { saveUserData() }, // Call save function
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Save Changes", fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {
                        signOutUser(navController =navController )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(
                        text = "Sign Out",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Function to Sign Out the User
fun signOutUser(navController: NavController) {
    val firebaseAuth = FirebaseAuth.getInstance()

    // Sign out the user
    firebaseAuth.signOut()

    // Check if the user is actually signed out
    val currentUser = firebaseAuth.currentUser
    if (currentUser == null) {
        // User successfully signed out
        navController.navigate("Onboard1") {
            // Clear backstack
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    } else {
        // Debug: User still exists
        Log.e("SignOutError", "User is still signed in: ${currentUser.uid}")
    }
}





@Composable
fun InputField(
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val input = text.text
        val formatted = buildString {
            var i = 0
            var formattedLength = 0
            while (i < input.length && formattedLength < 10) {
                append(input[i])
                formattedLength++
                if (formattedLength == 2 || formattedLength == 5) append("/")
                i++
            }
        }

        // Correct mapping of transformed offsets to original offsets
        val originalToTransformed = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // Handle offsets to transformed positions based on slashes
                return when (offset) {
                    2, 5 -> offset + 1  // If it's the 3rd or 6th character, add 1 for the slash
                    else -> offset
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Handle transformed offset back to original position (removes slashes)
                return when (offset) {
                    3, 6 -> offset - 1  // If it's after a slash, move back one position
                    else -> offset
                }
            }
        }

        return TransformedText(AnnotatedString(formatted), originalToTransformed)
    }
}

