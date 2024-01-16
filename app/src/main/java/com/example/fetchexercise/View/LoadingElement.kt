package com.example.fetchexercise.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Generic Loading Bar that is centered in the view. Used when data
 * is being fetched from the API, but no error has been encountered yet.
 */
@Composable
fun LoadingElement(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        CircularProgressIndicator()
        Spacer(Modifier.width(16.dp))
        Text("Loading...")
    }
}

// Preview for the Loading Element
@Preview(showBackground = true)
@Composable
fun LoadingElementPreview(){
    LoadingElement()
}