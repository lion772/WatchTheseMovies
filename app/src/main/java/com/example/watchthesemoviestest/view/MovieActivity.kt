package com.example.watchthesemoviestest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.watchthesemoviestest.R
import com.example.watchthesemoviestest.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieActivity : AppCompatActivity() {
    private val viewModel:MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.refresh()
        setAdapter()
    }
}