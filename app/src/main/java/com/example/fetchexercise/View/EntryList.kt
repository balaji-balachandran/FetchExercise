package com.example.fetchexercise.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchexercise.ViewModel.EntryViewModel

/**
 * Entry List object that displays data fetched by the view model passed
 * into it. Shows the list, error state, and loading screen
 *
 * @param viewModel model from which the Entry List gets its state and
 * retrieves data.
 */
@Composable
fun EntryList(viewModel: EntryViewModel = viewModel()) {
    // State variables of the Entry List
    val entries by viewModel.entries.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()

    // Fetch data when the view is created
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
    }

    Box(contentAlignment = Alignment.Center) {

        if (isLoading) {
            LoadingElement()
        } else if (isError) {
            FailedConnection()
        } else {
            // Utilize Lazy Column to efficiently handle rendering of elements
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                itemsIndexed(entries) { index, entry ->
                    // Add additional space on top for the first element
                    if (index == 0) Spacer(modifier = Modifier.height(12.dp))

                    EntryItem(entry)
                    Spacer(modifier = Modifier.height(3.dp))
                    if (index == entries.size - 1) Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        // Refresh Button
        ExtendedFloatingActionButton(
            onClick = { viewModel.fetchData() }, modifier = Modifier
                .padding(
                    horizontal = 28.dp, vertical = 12.dp
                )
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "Refresh",
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(20f, TextUnitType.Sp)
            )
        }
    }
}