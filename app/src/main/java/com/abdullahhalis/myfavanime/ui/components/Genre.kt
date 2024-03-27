package com.abdullahhalis.myfavanime.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun GenreItem(
    name: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondary),
        modifier = modifier.heightIn(max = 30.dp)
    ) {
        Text(text = name, style = MaterialTheme.typography.labelSmall.copy(
            color = Color.Black,
            textAlign = TextAlign.Center,
        ), maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp))
    }
}