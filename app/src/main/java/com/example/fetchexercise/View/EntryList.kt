package com.example.fetchexercise.View

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchexercise.ViewModel.EntryViewModel

@Composable
fun EntryList(viewModel: EntryViewModel = viewModel()) {

    val entries by viewModel.entries.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()

    // Fetch data when the List Object is created
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
    }

    Box(contentAlignment = Alignment.Center){

        if (isLoading) {
            // Show the loading animation
            Row(
                Modifier.height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(16.dp)
                )
                Text("Loading Your data...")
            }
        }
        else if(isError != null){
            Row(
                Modifier.height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(text="Connection Failed. Check your Internet connection and try again.")
            }
        }
        else{
            // Utilize Lazy to efficiently handle rendering of elements

            LazyColumn(modifier = Modifier
                .padding(12.dp)
                .fillMaxHeight()) {
                items(entries) { entry ->
                    EntryItem(entry)
                    Spacer(modifier = Modifier.height(3.dp))
                }
            }
        }
        ExtendedFloatingActionButton(onClick = { viewModel.fetchData() },
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
                .fillMaxWidth()
                .align(Alignment.BottomCenter)) {
            Text("Refresh")
        }
    }
}
