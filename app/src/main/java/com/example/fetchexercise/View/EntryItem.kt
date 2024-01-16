package com.example.fetchexercise.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.fetchexercise.Model.Entry
import com.example.fetchexercise.ui.theme.FetchExerciseTheme

@Composable
fun EntryItem(entry: Entry){
    ElevatedCard(elevation = CardDefaults.cardElevation(
        defaultElevation = 4.dp
    ), colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surface,
    ), modifier = Modifier
        .padding(horizontal = 28.dp, vertical = 1.dp)
        .fillMaxWidth()
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)){

            val hue = 360f * entry.listId / 4.0f
            val boxColor = Color.hsl(hue = hue, saturation = 0.67f, lightness = 0.72f)
            Box(modifier = Modifier
                .background(boxColor)
                .fillMaxHeight()
                .width(8.dp))
            Column(Modifier.padding(12.dp)) {
                Text(entry.name ?: "", fontWeight = FontWeight.Bold, fontSize = TextUnit(32f, TextUnitType.Sp))
                Text(text="List ID: ${entry.listId}")
                Text("ID: ${entry.id}")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntryItemPreview() {
    FetchExerciseTheme {
        EntryItem(Entry(id=12, name="Item 12", listId = 2))
    }
}