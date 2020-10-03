package com.example.watchthesemoviestest.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.watchthesemoviestest.R

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}


fun getProgressDrawable(context: Context) =
    CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }

@BindingAdapter("android:imageUrl")
fun loadImageApi(view:ImageView, url: String?){
    view.loadImage(url, getProgressDrawable(view.context))
}

@BindingAdapter("android:movieImage")
fun loadMovieImageApi(view:ImageView, url: String?){
    view.loadImage(url, getProgressDrawable(view.context))
}