package com.example.versevalult.UserINterface.DataClass



data class Poem(
    val image: String = "", // URL of the image
    val title: String = "", // Title of the poem
    val linecount: String = "", // Total lines in the poem as a String
    val lines: List<String> = emptyList(), // Title (or could be a list if you fetch lines separately)
    val author: String = "" // Author of the poem
)


