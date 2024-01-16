package com.example.fetchexercise.ViewModel

import androidx.lifecycle.ViewModel
import com.example.fetchexercise.Model.Entry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class to interact with the fetch-hiring API. Utilizies FetchAPI interface.
 * Contains state that is tracked by the EntryList View.
 */
class EntryViewModel : ViewModel() {
    // Keep track of list of entries, if we are still fetching entries,
    // and if we encountered an error
    private val _entries : MutableStateFlow<List<Entry>> = MutableStateFlow(emptyList())
    val entries: StateFlow<List<Entry>> = _entries.asStateFlow()

    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isError : StateFlow<Boolean> = _isError.asStateFlow()

    /**
     * Function to fetch data from the fetch-hiring site using Retrofit. Updates
     * the state variables maintained by the EntryViewModel Class based on the
     * response so that the UI can be updated accordingly
     */
    fun fetchData() {
        _isLoading.value = true
        val baseURL = "https://fetch-hiring.s3.amazonaws.com/"
        val api = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FetchAPI::class.java)

        api.getEntries().enqueue(object : Callback<List<Entry>> {
            override fun onResponse(call: Call<List<Entry>>, response: Response<List<Entry>>) {
                if(response.isSuccessful){
                    response.body()?.let{ entriesResponse ->

                        // Update the state variables
                        _entries.value = filterAndSortEntries(entriesResponse)
                        _isError.value = false
                        _isLoading.value = false
                    }
                }
            }

            override fun onFailure(call: Call<List<Entry>>, t: Throwable) {
                // Updates state variables to notify user of error
                _isError.value = true
                _isLoading.value = false
            }

        })
    }

    /**
     * Helper function to validate and process the data by filtering
     * out null and empty name values and then sorting by listId then
     * name
     *
     * @param entries List of Entries.
     *
     * @return Filtered and Sorted List of Entries
     */
    private fun filterAndSortEntries(entries: List<Entry>): List<Entry> {
        return entries.filter { entry ->
            // Filters Entries Leaving Non-Null and Non-Empty
            !entry.name.isNullOrEmpty()
        }
            // Sort the filtered list by listId, then name
            .sortedWith(
                compareBy(Entry::listId, Entry::name)
            )
    }
}