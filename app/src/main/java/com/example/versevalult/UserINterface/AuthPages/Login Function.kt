package com.example.versevalult.UserINterface.AuthPages

import com.google.firebase.auth.FirebaseAuth

fun loginUser(
    email: String,
    password: String,
    onResult: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult("success")
            } else {
                onResult("Login error: ${task.exception?.message}")
            }
        }
}
