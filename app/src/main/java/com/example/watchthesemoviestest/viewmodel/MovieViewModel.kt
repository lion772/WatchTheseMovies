package com.example.watchthesemoviestest.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.watchthesemoviestest.model.Movie
import com.example.watchthesemoviestest.moviecase.MovieUseCase
import com.example.watchthesemoviestest.network.ApiResult
import kotlinx.coroutines.launch

class MovieViewModel(private val movieUseCase: MovieUseCase, application: Application
): BaseViewModel(application) {


    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _moviesLoadErrorMessage = MutableLiveData<String>()
    val moviesLoadErrorMessage: LiveData<String> get() = _moviesLoadErrorMessage

    private val _moviesLoadError = MutableLiveData<Boolean>()
    val moviesLoadError: LiveData<Boolean> get() = _moviesLoadError

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun refresh(){
        fetchMoviesFromRemote()
    }

    private fun fetchMoviesFromRemote() {
        _loading.value = true
        launch {
            when(val response = movieUseCase.getMoviesAwait()){
                is ApiResult.Success -> {
                    response.data?.let {MoviesList ->
                        moviesRetrieved(MoviesList)
                        Toast.makeText(getApplication(), "Movies retrieved from endpoint", Toast.LENGTH_SHORT).show()
                    }
                }
                is ApiResult.Error ->{
                    _moviesLoadErrorMessage.postValue(response.message)
                    _moviesLoadError.postValue(true)
                    _loading.postValue(false)
                }
            }
        }
    }

    private fun moviesRetrieved(moviesList: List<Movie>) {
        _movies.postValue(moviesList)
        _moviesLoadError.postValue(false)
        _loading.postValue(false)
    }


}