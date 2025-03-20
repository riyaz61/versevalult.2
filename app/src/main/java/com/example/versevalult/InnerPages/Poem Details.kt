package com.example.versevalult.InnerPages

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.versevalult.R
import com.example.versevalult.UserINterface.Components.TopSection
import java.net.URLEncoder


@Composable
fun PoemDetailsScreen(navController: NavHostController, lines: List<String>, title: String) {
    val cleanedLines = cleanPoemLines(lines)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        item {
            TopSection()
        }

        item {
            AsyncImage(
                model = "https://image.pollinations.ai/prompt/${URLEncoder.encode(title)}",
                error= painterResource(id = R.drawable.login),

                contentScale = ContentScale.Crop,
                contentDescription = "Poem Image",
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        item {
            PoemContent(cleanedLines)
        }
    }
}






@Composable
fun PoemContent(lines: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp), // Space between lines
        modifier = Modifier.fillMaxWidth()
    ) {
        lines.forEach { line ->
            Text(
                text = line,
                fontSize = 16.sp,
                color = Color.Black,
                lineHeight = 24.sp // Adjust line spacing
            )
        }
    }
}




@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Author's image
        Icon(
            painter = painterResource(id = R.drawable.login), // Replace with your author image
            contentDescription = "Author's Image",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            tint = Color.Unspecified // Remove default tint
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Author's name and poem count
        Column {
            Text(
                text = "Yori Alvarela",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "7 Poems",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Share icon
        IconButton(onClick = { /* TODO: Add share logic */ }) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share",
                tint = Color.White
            )
        }
    }
}




@Composable
@Preview
fun SHower(){
    PoemDetailsScreen(navController = rememberNavController(), lines = listOf("When the buds began to burst, ", "Long ago, with Rose the First", "I was walking; joyous then", "Far above all other men, ", "Till before us up there stood", "Britonferry\u0027s oaken wood, ", "Whispering, Happy as thou art, ", "Happiness and thou must part.", "Many summers have gone by", "Since a Second Rose and I", "(Rose from the same stem) have told", "This and other tales of old.", "She upon her wedding day", "Carried home my tenderest lay:", "From her lap I now have heard", "Gleeful, chirping, Rose the Third.", "Not for her this hand of mine", "Rhyme with nuptial wreath shall twine  Rhyme with nuptial wreath shall twinRhyme with nuptial wreath shall twin;", "Cold and torpid it must lie, ", "Mute the tongue, and closed the eye.") , title ="Test" )
}

fun cleanPoemLines(rawLines: List<String>): List<String> {
    return rawLines.map { line ->
        line.replace("\\u0027", "'") // Convert escaped apostrophes
            .replace("\\n", "")      // Remove newlines
            .replace("\\s+".toRegex(), " ")
            .replace('"',' ')// Replace extra spaces with single space
            .trim()                  // Remove leading/trailing whitespace
    }.filter { it.isNotEmpty() } // Remove empty lines
}



