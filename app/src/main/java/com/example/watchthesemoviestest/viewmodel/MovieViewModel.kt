package com.example.watchthesemoviestest.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.watchthesemoviestest.model.Images
import com.example.watchthesemoviestest.model.Movie
import com.example.watchthesemoviestest.model.ResultMovie
import com.example.watchthesemoviestest.moviecase.MovieUseCase
import com.example.watchthesemoviestest.network.ApiResult
import com.example.watchthesemoviestest.repository.MovieDatabase
import com.example.watchthesemoviestest.util.SharedPreferencesHelper
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase, application: Application
) : BaseViewModel(application) {

    private var prefHelper = SharedPreferencesHelper(getApplication())

    private val _movies = MutableLiveData<List<ResultMovie>>()
    val movies: LiveData<List<ResultMovie>> get() = _movies
    private val _movie = MutableLiveData<ResultMovie>()
    val movie: LiveData<ResultMovie> get() = _movie

    private val _moviesLoadErrorMessage = MutableLiveData<String>()
    val moviesLoadErrorMessage: LiveData<String> get() = _moviesLoadErrorMessage

    private val _moviesLoadError = MutableLiveData<Boolean>()
    val moviesLoadError: LiveData<Boolean> get() = _moviesLoadError

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _images = MutableLiveData<Images>()
    val images: LiveData<Images> get() = _images

    fun refresh() {
        val updateTime = prefHelper.getUpdateTime()
        updateTime?.let { Time ->
            if (Time != 0L && System.nanoTime() - Time < REFRESH_TIME){
                fetchMoviesFromDatabase()
            } else{
                fetchMoviesFromRemote()
            }
        }
    }

    fun refreshByPassCache() {
        fetchMoviesFromRemote()
    }

    private fun fetchMoviesFromDatabase() {
        _loading.value = true
        launch {
            val movies = MovieDatabase(getApplication()).movieDao().getAllMovies()
            moviesRetrieved(movies)
            Toast.makeText(getApplication(), "Dogs retrieved from DB", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchMoviesFromRemote() {
        _loading.value = true
        launch {
            when (val response = movieUseCase.getMoviesAwait()) {
                is ApiResult.Success -> {
                    response.data?.let { Movie ->
                        storeMoviesLocally(Movie)
                    }
                }
                is ApiResult.Error -> {
                    errorMessage(response.message)
                }
            }
            when (val response = movieUseCase.getImagesAwait()) {
                is ApiResult.Success -> {
                    response.data?.let { Images ->
                        imagesRetrieved(Images.movieImages)

                    }
                }

                is ApiResult.Error -> {
                    errorMessage(response.message)
                }
            }
        }
    }

    private fun storeMoviesLocally(movie: Movie) {
        launch {
            val dao = MovieDatabase(getApplication()).movieDao()
            dao.deleteAllMovies()
            val resultList = movie.resultMovies
            val insertDatabase = dao.insertAll(*resultList.toTypedArray())

            for (i in resultList.indices) {
                resultList[i].uuid = insertDatabase[i].toInt()
            }
            moviesRetrieved(movie.resultMovies)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    private fun moviesRetrieved(moviesList: List<ResultMovie>) {
        _movies.postValue(moviesList)
        _moviesLoadError.postValue(false)
        _loading.postValue(false)
    }


    private fun imagesRetrieved(imagesList: Images) {
        _images.postValue(imagesList)
        _loading.postValue(false)
    }

    private fun errorMessage(message: String) {
        _moviesLoadErrorMessage.postValue(message)
        _moviesLoadError.postValue(true)
        _loading.postValue(false)
    }


    companion object {
        private const val PATTERN = "dd/MM/yyyy"
        private const val REFRESH_TIME = 10 * 1000 * 1000 * 1000L
    }

}