package com.example.versevalult.UserINterface.MainPages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.versevalult.R
import com.example.versevalult.UserINterface.Components.LikedPoem
import com.example.versevalult.UserINterface.Components.TopSection

@Composable
fun LovePage() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.background,

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Top, // Align items to the top
            horizontalAlignment = Alignment.Start // Align items to the start
        ) {
            TopSection() // Top section
            Spacer(modifier = Modifier.height(8.dp))
            PoemOfTheDaySection() // Poem of the Day section
        }
    }
}


@Composable
fun PoemOfTheDaySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {


        // Scrollable Poems List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f), // Ensures LazyColumn takes remaining space
            verticalArrangement = Arrangement.spacedBy(12.dp) // Add spacing between items
        ) {
            // Poems in a list
            val poems = List(10) { "Poem Title $it" to "Author $it" }
            items(poems.size) { index ->
                val (poemName, authorName) = poems[index]
                LikedPoem(
                    imageResId = R.drawable.login,
                    poemName = poemName,
                    authorName = authorName,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}




