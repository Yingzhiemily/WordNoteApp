package com.example.wordnote.ui.feature.search

import android.content.Context
import com.example.wordnote.ui.model.WordResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface SearchApi {
    @GET("entries/en/{word}")
    suspend fun getWord(@Path("word") word: String): Response<List<WordResult>>
}

object ApiClient {
        
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.dictionaryapi.dev/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val searchApi: SearchApi = retrofit.create(SearchApi::class.java)
}
