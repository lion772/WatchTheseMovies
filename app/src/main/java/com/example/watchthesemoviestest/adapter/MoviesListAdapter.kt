package com.example.watchthesemoviestest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.watchthesemoviestest.R
import com.example.watchthesemoviestest.databinding.MoviesListBinding
import com.example.watchthesemoviestest.model.Movie
import com.example.watchthesemoviestest.model.ResultMovie
import com.example.watchthesemoviestest.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.movies_list.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MoviesListAdapter(private var movieList: ArrayList<ResultMovie> = arrayListOf()):
    RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    var onClickMovie:((ResultMovie) -> Unit)? = null

    fun updateMovieList(newMovieList: List<ResultMovie>){
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(DataBindingUtil.inflate(inflater,
            R.layout.movies_list, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int)
            = holder.bind(position)

    override fun getItemCount()= movieList.size

    inner class MovieViewHolder(var view: MoviesListBinding): RecyclerView.ViewHolder(view.root){

        init {
            itemView.setOnClickListener{
                onClickMovie?.invoke(movieList[adapterPosition])
            }
        }

        fun bind(position: Int){
            view.result = movieList[position]

            for (i in movieList.indices){
                /*val eventDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date.parse(movieList[i].releaseDate))
                itemView.tv_movie_release_date.text = eventDate.toString()*/
            }
        }
    }
}