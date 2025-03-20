package com.example.versevalult.InnerPages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.gson.Gson
import com.example.versevalult.R
import com.example.versevalult.UserINterface.Components.TopSection
import com.example.versevalult.UserINterface.DataClass.Poem
import java.net.URLEncoder

@Composable
fun PoemsGridScreen(navController: NavController, poems: List<Poem>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TopSection()
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(poems) { poem ->
                PoemCard2(
                    imageResId = poem.image,
                    poemName = poem.title,
                    totalLines = poem.lines.size,
                    navController,
                    poem.lines
                )
            }
        }
    }
}

@Composable
fun PoemCard2(
    imageResId: String,
    poemName: String,
    totalLines: Int,
    navController: NavController,
    lines: List<String>
) {

    val encodedPrompt = URLEncoder.encode(poemName, "UTF-8")
    val imageUrl = "https://image.pollinations.ai/prompt/$encodedPrompt"
    val gson = Gson()
    val linesJson = gson.toJson(lines)


    Log.d("PoemCard2", "PoemCard2: ${"https://image.pollinations.ai/prompt/${encodedPrompt}"}")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp) // Dynamic height adjustment
            .clip(RoundedCornerShape(12.dp))
            .shadow(4.dp, RoundedCornerShape(12.dp)).
                clickable {

                    navController.navigate("Poem_Details/${linesJson}/${poemName}")

                }
            .background(MaterialTheme.colorScheme.surface),

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image Section
            AsyncImage(
                model = imageUrl,
                error= painterResource(id = R.drawable.login),
                placeholder = painterResource(id = R.drawable.login),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Title Section
            Text(
                text = poemName,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Total Lines Section
            Text(
                text = "$totalLines lines",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

//
//@Preview
//@Composable
//fun PoemsGridScreenPreview() {
//    val samplePoems = listOf(
//        Poem(
//            image = R.drawable.login,
//            title = "Poem 1",
//            linecount = 125,
//            lines = listOf("I.", "Here take no care, take here", "Nor ought of Art or Labour"),
//            author = "Test"
//        ),
//        Poem(
//            image = R.drawable.login,
//            title = "Poem 2",
//            linecount = 100,
//            lines = listOf("Line one of Poem 2", "Line two of Poem 2", "Line three of Poem 2"),
//            author = "Test"
//        ),
//        Poem(
//            image = R.drawable.login,
//            title = "Poem 3",
//            linecount = 75,
//            lines = listOf("This is a sample poem line", "Another line appears here", "And one more for context"),
//            author = "Test"
//        ),
//        Poem(
//            image = R.drawable.login,
//            title = "Poem 3",
//            linecount = 75,
//            lines = listOf("This is a sample poem line", "Another line appears here", "And one more for context"),
//            author = "Test"
//        )
//    )
//
//    PoemsGridScreen(rememberNavController(),poems = samplePoems)
//}


