package com.example.watchthesemoviestest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_table")
data class ResultMovie(
    val popularity: Double,
    val id: Long,
    val video: Boolean,
    val voteCount: Long,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: String,
    val title: String,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String,
    val originalLanguage: OriginalLanguageMovie,
    val originalTitle: String,
    val genreIDS: List<Long>,
    val backdropPath: String,
    val adult: Boolean,
    val overview: String,
    val posterPath: String
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}