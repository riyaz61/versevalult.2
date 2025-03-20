package com.example.versevalult.UserINterface.AuthPages

import com.example.versevalult.UserINterface.DataClass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.FirebaseDatabase

fun registerUser(
    fullName: String,
    email: String,
    password: String,
    location: String,
    onResult: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().reference

    // Check for empty fields
    if (fullName.isBlank() || email.isBlank() || password.isBlank() || location.isBlank()) {
        onResult("All fields are required.")
        return
    }

    // Validate password length
    if (password.length < 6) {
        onResult("Password must be at least 6 characters long.")
        return
    }

    // Create user in Firebase Authentication
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val firebaseUser = auth.currentUser
                val userId = firebaseUser?.uid ?: return@addOnCompleteListener

                // Create a User object
                val user = User(
                    name = fullName,
                    email = email,
                    location = location,
                    LikedPoems = null // Initialize as empty or add data later
                )

                // Save user in Firebase Realtime Database
                database.child("users").child(userId).setValue(user)
                    .addOnSuccessListener {
                        onResult("Registration successful!")
                    }
                    .addOnFailureListener { e ->
                        // Roll back Firebase Authentication if database write fails
                        firebaseUser.delete().addOnCompleteListener {
                            onResult("Database error: ${e.message}. Registration rolled back.")
                        }
                    }
            } else {
                // Handle authentication errors
                val errorMessage = when (task.exception) {
                    is FirebaseAuthWeakPasswordException -> "Weak password. Please choose a stronger password."
                    is FirebaseAuthInvalidCredentialsException -> "Invalid email address format."
                    is FirebaseAuthUserCollisionException -> "An account with this email already exists."
                    else -> "Authentication error: ${task.exception?.message}"
                }
                onResult(errorMessage)
            }
        }
        .addOnFailureListener { e ->
            // Catch any unexpected exceptions
            onResult("Unexpected error: ${e.message}")
        }
}
