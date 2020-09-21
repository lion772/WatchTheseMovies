package com.example.watchthesemoviestest.network

import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("3/movie/550?api_key=b3bb363a5285a5d43a349b74bd0454aa")
    suspend fun getMoviesApi(): Response<List<>>
}