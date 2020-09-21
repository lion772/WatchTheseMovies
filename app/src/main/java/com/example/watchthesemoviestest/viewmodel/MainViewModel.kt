package com.example.watchthesemoviestest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.watchthesemoviestest.moviecase.MovieUseCase

class MainViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
}