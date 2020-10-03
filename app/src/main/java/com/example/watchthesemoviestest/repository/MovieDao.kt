package com.example.watchthesemoviestest.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.watchthesemoviestest.model.ResultMovie

@Dao
interface MovieDao {

    @Insert suspend fun insertAll(vararg results: ResultMovie): List<Long>

    @Query("SELECT * FROM movie_table")
    suspend fun getAllMovies(): List<ResultMovie>

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie_table WHERE id =:movieId")
    suspend fun getMovieById(movieId:Int): ResultMovie

}