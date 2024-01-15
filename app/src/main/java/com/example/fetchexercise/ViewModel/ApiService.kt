package com.example.fetchexercise.ViewModel

import com.example.fetchexercise.Model.Entry
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    fun getEntries(): Call<List<Entry>>
}