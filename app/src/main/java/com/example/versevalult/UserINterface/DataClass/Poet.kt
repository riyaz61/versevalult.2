package com.example.versevalult.UserINterface.DataClass

data class Poet(
    val image: String="0", // Resource ID for the poet's image
    val name: String="",    // Poet's name
    val poem: List<Poem> = emptyList(),  // Total number of poems
)
