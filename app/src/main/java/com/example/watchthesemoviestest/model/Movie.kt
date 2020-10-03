package com.example.watchthesemoviestest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "image_table")
data class Movie(
    @ColumnInfo(name = "results")
    @SerializedName("results")
    val resultMovies: List<ResultMovie>,
    val page: Long,
    val totalResults: Long,
    val dates: Dates,
    val totalPages: Long
)/*{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}*/