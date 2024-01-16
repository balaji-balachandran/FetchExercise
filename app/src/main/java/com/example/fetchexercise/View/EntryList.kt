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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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
            LoadingElement()
        }
        else if(isError){
            FailedConnection()
        }
        else{
            // Utilize Lazy Column to efficiently handle rendering of elements
            LazyColumn(modifier = Modifier
                .fillMaxHeight()) {


                itemsIndexed(entries) { index, entry ->
                    // Add additional space on top for the first element
                    if(index == 0)
                        Spacer(modifier = Modifier.height(12.dp))

                    EntryItem(entry)
                    Spacer(modifier = Modifier.height(3.dp))
                    if(index == entries.size - 1)
                        Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        // Refresh Button
        ExtendedFloatingActionButton(onClick = { viewModel.fetchData() },
            modifier = Modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 12.dp
                )
                .fillMaxWidth()
                .align(Alignment.BottomCenter)) {
            Text("Refresh", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EntryListPreview(){
    EntryList()
}