package com.example.versevault.UserInterface.MainPages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.versevalult.R
import com.example.versevalult.UserINterface.Components.PoemCard
import com.example.versevalult.UserINterface.Components.PoetCard
import com.example.versevalult.UserINterface.Components.getSamplePoets
import com.example.versevalult.UserINterface.DataClass.Poet

@Composable
fun HomePage() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            TopSection()
            Spacer(modifier = Modifier.height(4.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Top Section


                // Poem of the Day Section
                item { PoemOfTheDaySection() }

                // Explore Other Poets Section
                item { ExploreOtherPoets() }
            }
        }

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
                imageResId = poet.imageResId,
                poetName = poet.name,
                totalPoems = poet.totalPoems,
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

@Composable
fun ExploreOtherPoets() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Section Header with an icon
        Text(
            text = "See Our Authors",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        // Display Poet List
        PoetList(poets = getSamplePoets())
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 10.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = { /* Handle menu click */ }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = "VerseVault",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            IconButton(onClick = { /* Handle search */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            IconButton(onClick = { /* Handle notifications */ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun PoemOfTheDaySection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Section Header with an icon
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Poem of the Day",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        // LazyRow for Poem Cards
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(5) { index -> // Adjust the number of items as needed
                PoemCard(
                    imageResId = R.drawable.login, // Replace with your drawable
                    poemName = "Poem Title $index",
                    authorName = "Author $index",
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                        .shadow(2.dp, RoundedCornerShape(8.dp)) // Added shadow to Poem cards
                )
            }
        }
    }
}

@Composable
@Preview
fun HomePagePreview() {
    HomePage()
}
