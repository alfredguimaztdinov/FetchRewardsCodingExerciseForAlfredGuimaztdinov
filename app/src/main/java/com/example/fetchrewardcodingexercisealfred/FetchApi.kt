package com.example.fetchrewardcodingexercisealfred

import retrofit2.Response
import retrofit2.http.GET

interface FetchApi {

    @GET("/hiring.json")
  suspend fun getId(): Response<List<Item>>

}