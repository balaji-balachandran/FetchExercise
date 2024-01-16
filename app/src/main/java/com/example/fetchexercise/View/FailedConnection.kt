package com.example.fetchexercise.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fetchexercise.R

/**
 * Connection Failed field that is displayed when a connection fails
 */
@Composable
fun FailedConnection() {
    Row(
        modifier = Modifier.padding(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.wifi_off),
            contentDescription = "Connection Failed",
            contentScale = ContentScale.Fit,

            // Colors the Icon according to the theme
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
        )
        Spacer(Modifier.width(15.dp))
        Text(text = "Connection Failed. Please check your Internet connection and try again.")
    }
}

// Preview for the Connection Failed Element
@Preview(showBackground = true)
@Composable
fun FailedConnectionPreview() {
    FailedConnection()
}