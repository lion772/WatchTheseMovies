package com.example.watchthesemoviestest.network

import com.example.watchthesemoviestest.model.Movie
import com.example.watchthesemoviestest.model.MovieImage
import com.example.watchthesemoviestest.model.ResultMovie
import retrofit2.Response
import retrofit2.http.GET

interface MovieApi {

    @GET("3/movie/now_playing?api_key=b3bb363a5285a5d43a349b74bd0454aa&language=pt-BR&page=1")
    suspend fun getMoviesApi(): Response<Movie>

    @GET("3/configuration?api_key=b3bb363a5285a5d43a349b74bd0454aa")
    suspend fun getImagesApi(): Response<MovieImage>
}