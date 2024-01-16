package com.example.fetchexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import com.example.fetchexercise.View.EntryList
import com.example.fetchexercise.ui.theme.FetchExerciseTheme

/**
 * Fetch Take Home Exercise App that fetches data from a server, processes
 * the resultant JSON, then displays the sorted and filtered data to the
 * user.
 *
 * @author Balaji Balachandran
 * @since 1/16/24
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Applies the splash screen to the app
        installSplashScreen().apply {}

        setContent {
            FetchExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Shows the UI of the App
                    EntryList()
                }
            }
        }
    }
}