package com.example.watchthesemoviestest.repository

import android.content.Context
import com.example.watchthesemoviestest.network.MovieApi

class MovieRepository(private val context: Context, private val movieApi: MovieApi): BaseRepository() {

    suspend fun getMovies() = safeCallApi { movieApi.getMoviesApi() }
    suspend fun getImages() = safeCallApi { movieApi.getImagesApi() }
}