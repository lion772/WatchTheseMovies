package com.example.watchthesemoviestest.moviecase

import com.example.watchthesemoviestest.repository.MovieRepository

class MovieUseCase(private val movieRepository: MovieRepository) {

    suspend fun getMoviesAwait() = movieRepository.getMovies()
    suspend fun getImagesAwait() = movieRepository.getImages()
}