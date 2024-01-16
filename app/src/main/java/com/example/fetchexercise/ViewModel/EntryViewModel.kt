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

class EntryViewModel : ViewModel() {
    private val _entries : MutableStateFlow<List<Entry>> = MutableStateFlow(emptyList())
    val entries: StateFlow<List<Entry>> = _entries.asStateFlow()

    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError : MutableStateFlow<String?> = MutableStateFlow(null)
    val isError : StateFlow<String?> = _isError.asStateFlow()

    fun fetchData() {
        _isLoading.value = true
        val baseURL = "https://fetch-hiring.s3.amazonaws.com/"
        val api = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        api.getEntries().enqueue(object : Callback<List<Entry>> {
            override fun onResponse(call: Call<List<Entry>>, response: Response<List<Entry>>) {
                if(response.isSuccessful){
                    response.body()?.let{
                        val entryList = it.filter{entry ->
                            // Filters Entries Leaving Non-Null and Non-Empty
                            !entry.name.isNullOrEmpty()
                        }
                        // Sort the filtered list by listId, then name
                        .sortedWith(
                            compareBy(Entry::listId, Entry::name)
                        )

                        // Update the state variable
                        _entries.value = entryList
                        _isLoading.value = false
                    }
                }
            }

            override fun onFailure(call: Call<List<Entry>>, t: Throwable) {
                println("FAILED: ${t.message}")
                _isError.value = "There was an error fetching data"
                _isLoading.value = false
            }

        })
    }
}