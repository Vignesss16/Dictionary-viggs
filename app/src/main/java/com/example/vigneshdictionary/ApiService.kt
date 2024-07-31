package com.example.vigneshdictionary



import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("en/{word}")
    suspend fun fetchDefinition(@Path("word") word: String): Response<List<WordDefinition>>
}
