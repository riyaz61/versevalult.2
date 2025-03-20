package com.example.versevalult.UserINterface.DataClass

data class User(
    val name: String = "",
    val email: String = "",
    val location: String = "",
    val LikedPoems: List<Poem>? = null
)