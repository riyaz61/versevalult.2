package com.example.versevalult.UserINterface.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.versevalult.R
import com.example.versevalult.UserINterface.DataClass.Poet


@Composable
fun PoetCard(
    imageResId: String,
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

        AsyncImage(
            model = imageResId,
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
        Poet(name = "William Shakespeare", image = "https://image.pollinations.ai/prompt/user3", poem =  emptyList()),
        Poet(name = "Emily Dickinson",image = "https://image.pollinations.ai/prompt/user3", poem =emptyList()),
        Poet(name ="Robert Frost",image =  "https://image.pollinations.ai/prompt/user3", poem =emptyList()),
        Poet(name ="Langston Hughes",image =  "https://image.pollinations.ai/prompt/user3", poem =emptyList()),
        Poet(name ="Maya Angelou", image = "https://image.pollinations.ai/prompt/user3", poem =emptyList())
    )
}

@Preview
@Composable
fun PoetGridPreview() {
    MaterialTheme {
        PoetList(poets = getSamplePoets())
    }
}

@Composable
fun PoetList(poets: List<Poet>, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        poets.forEach { poet ->
            PoetCard(
                imageResId = poet.image,
                poetName = poet.name,
                totalPoems = poet.poem.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .shadow(4.dp, RoundedCornerShape(12.dp)) // Adding shadow effect
            )
        }
    }
}

