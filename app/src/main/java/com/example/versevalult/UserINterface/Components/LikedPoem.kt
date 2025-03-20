package com.example.versevalult.UserINterface.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.versevalult.R

@Composable
fun LikedPoem(
    imageResId: Int, // Resource ID for the image
    poemName: String,
    authorName: String,
    modifier: Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()

            .clip(RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF121212)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF121212)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image on top
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Poem Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)

                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Poem name
            Text(
                text = poemName,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Author name
            Text(
                text = "by $authorName",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp
                ),
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
@Preview
fun LikedPoem() {
    LikedPoem(
        imageResId = R.drawable.login, // Replace with an actual drawable resource ID
        poemName = "The Road Not Taken",
        authorName = "Robert Frost",
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .shadow(2.dp, RoundedCornerShape(8.dp))
    )
}
