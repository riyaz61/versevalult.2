package com.example.versevault.UserInterface.MainPages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.versevalult.UserINterface.Components.PoemCard
import com.example.versevalult.UserINterface.Components.PoetCard
import com.example.versevalult.UserINterface.Components.TopSection
import com.example.versevalult.UserINterface.DataClass.Poet
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.versevalult.InnerPages.cleanPoemLines
import com.example.versevalult.UserINterface.DataClass.Poem
import com.google.firebase.database.*
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun HomePage(navController: NavHostController) {
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
                item { PoemOfTheDaySection(navController) }

                // Explore Other Poets Section
                item { ExploreOtherPoets(navController) }
            }
        }

    }
}

@Composable
fun PoetList(navController: NavController,poets: List<Poet>, modifier: Modifier = Modifier) {
    val gson = Gson()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        poets.forEach { poet ->
            Log.d("poemCheck", "PoetList: ${poet.poem.size}")
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
                    .clickable {
                        // Serialize the poet.poem list to JSON

                        val jsonPoems = gson.toJson(poet.poem)

                        // Safely encode the JSON string for navigation
                        val encodedJsonPoems = URLEncoder.encode(jsonPoems, "UTF-8")

                        // Navigate with the encoded JSON string
                        navController.navigate("Poems/$encodedJsonPoems")
                    }

            )
        }
    }
}





@Composable
fun ExploreOtherPoets(navController: NavController) {

    val context = LocalContext.current
    var poems by remember { mutableStateOf(listOf<Poet>()) } // State to hold list of Poem objects
    val databaseReference = FirebaseDatabase.getInstance().getReference("poets")

    // Fetch data from Firebase
    LaunchedEffect(Unit) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val poemList = mutableListOf<Poet>()
                for (poemSnapshot in snapshot.children) {
                    val poem = poemSnapshot.getValue(Poet::class.java)
                    if (poem != null) {
                        poemList.add(poem)
                    }
                }
                poems = poemList

                Log.d("ExploreOtherPoets", "onDataChange: ${poemList}")
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to load poems: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
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
        PoetList(navController, poets = poems)
    }
}






@Composable
fun PoemOfTheDaySection(navController: NavHostController) {
    val context = LocalContext.current
    var poems by remember { mutableStateOf(listOf<Poem>()) } // State to hold list of Poem objects
    val databaseReference = FirebaseDatabase.getInstance().getReference("Poem of the week")

    // Fetch data from Firebase
    LaunchedEffect(Unit) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val poemList = mutableListOf<Poem>()
                for (poemSnapshot in snapshot.children) {
                    val poem = poemSnapshot.getValue(Poem::class.java)
                    if (poem != null) {
                        poemList.add(poem)
                    }
                }
                poems = poemList
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to load poems: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // UI
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Section Header
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
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            items(poems) { poem ->
                PoemCard(
                    imageResId = poem.image, // Replace with a placeholder resource if needed
                    poemName = poem.title, // Use title from Poem object
                    authorName = poem.author,
                    lines= poem.lines,// Use author from Poem object
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(5.dp)
                        .shadow(2.dp, RoundedCornerShape(8.dp)),
                    navcontroller= navController
                )
            }
        }
    }
}



@Composable
@Preview
fun HomePagePreview() {
    HomePage(rememberNavController())
}