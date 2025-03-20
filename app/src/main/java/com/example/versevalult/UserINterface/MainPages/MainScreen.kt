package com.example.versevalult.UserINterface.MainPages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.versevalult.R
import com.example.versevalult.UserINterface.DataClass.NavItem
import com.example.versevault.UserInterface.MainPages.HomePage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val navItemList = listOf(
        NavItem("Home", painterResource(id = R.drawable.home)),

        NavItem("Profile", painterResource(R.drawable.user))
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = navItem.icon, // Use `Painter` for raster images
                                contentDescription = "Icon"
                            )
                        },
                        label = { Text(text = navItem.label) }
                    )
                }
            }
        }
    ) {
        // Pass the NavController to each screen
        when (selectedIndex) {
            0 -> HomePage(navController) // Pass NavController here

            1 -> EditPage(navController) // Pass NavController here
        }
    }
}