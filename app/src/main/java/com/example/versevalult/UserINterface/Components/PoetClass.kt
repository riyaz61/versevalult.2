package com.example.versevalult.UserINterface.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.versevalult.R
import com.example.versevalult.UserINterface.DataClass.Poet
import com.example.versevault.UserInterface.MainPages.PoetList

@Composable
fun PoetCard(
    imageResId: Int,
    poetName: String,
    totalPoems: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth() // Set fixed width for each card
            .padding(8.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            ).border(1.dp, Color.Gray, shape = MaterialTheme.shapes.medium)
            .padding(5.dp), // Inner padding for the card content
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Poet Image

        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Poem Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)

                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentScale = ContentScale.Crop,
        )

        // Poet Name
        Text(
            text = poetName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 8.dp)
        )

        // Total Poems
        Text(
            text = "$totalPoems Poems",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}





// Sample Data Class


// Sample Data Function
fun getSamplePoets(): List<Poet> {
    return listOf(
        Poet("William Shakespeare", R.drawable.login, 38),
        Poet("Emily Dickinson", R.drawable.login, 1800),
        Poet("Robert Frost", R.drawable.login, 100),
        Poet("Langston Hughes", R.drawable.login, 50),
        Poet("Maya Angelou", R.drawable.login, 100)
    )
}

@Preview
@Composable
fun PoetGridPreview() {
    MaterialTheme {
        PoetList(poets = getSamplePoets())
    }
}
