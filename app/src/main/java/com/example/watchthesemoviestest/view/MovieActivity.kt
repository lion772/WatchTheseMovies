package com.example.watchthesemoviestest.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.watchthesemoviestest.R
import com.example.watchthesemoviestest.adapter.MoviesListAdapter
import com.example.watchthesemoviestest.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movies_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity() {
    private val viewModel:MovieViewModel by viewModel()
    private var backdroppath:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.refresh()
        setAdapter()
        refresh_layout.setOnRefreshListener{
            rv_movies.visibility = View.GONE
            tv_error.visibility = View.GONE
            progressBar.visibility = View.GONE
            viewModel.refresh()
            refresh_layout.isRefreshing = false
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.images.observe(this) {images ->
            Glide.with(this).load(Uri.parse("${images.baseURL} " +
                    "+ ${images.backdropSizes} + $backdroppath")).into(iv_movie)
            /*image.movieImages.baseURL +
                    image.movieImages.backdropSizes[0] + result.backdropPath*/
        }

        viewModel.movies.observe(this) { movies ->
            movies?.let {
               /* for (i in movies.indices){
                    backdroppath = movies[i].backdropPath
                }*/

                rv_movies.visibility = View.VISIBLE
                (rv_movies.adapter as MoviesListAdapter).updateMovieList(movies)
            }
        }

        viewModel.moviesLoadErrorMessage.observe(this){ isErrorMessage->
            isErrorMessage?.let {
                tv_error.text = it
            }
        }

        viewModel.loading.observe(this) { isLoading->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    rv_movies.visibility = View.GONE
                    tv_error.visibility = View.GONE
                }
            }
        }
    }

    private fun setAdapter() {
        rv_movies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = MoviesListAdapter()
        }
        (rv_movies.adapter as MoviesListAdapter).onClickMovie = {Movie ->
            //Toast.makeText(applicationContext, "Nome do Filme: ${Movie.title}", Toast.LENGTH_SHORT).show()
        }
    }
}