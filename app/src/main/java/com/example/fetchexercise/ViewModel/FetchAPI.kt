package com.example.fetchexercise.ViewModel

import com.example.fetchexercise.Model.Entry
import retrofit2.Call
import retrofit2.http.GET

/**
 * API Class for executing GET requests to the fetch-hiring site.
 * Currently only gets '/hiring.json'
 */
interface FetchAPI {
    @GET("hiring.json")
    fun getEntries(): Call<List<Entry>>
}