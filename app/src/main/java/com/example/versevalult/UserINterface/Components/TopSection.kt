package com.example.versevalult.UserINterface.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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