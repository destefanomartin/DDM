package com.utn.primerparcial.movies.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.utn.primerparcial.movies.models.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies ORDER BY id")
    fun fetchAllMovies(): MutableList<Movie?>?

    @Query("SELECT * FROM movies WHERE id = :id")
    fun fetchMovieById(id: Int): Movie?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): MutableList<Movie?>?

    @Delete
    fun delete(movie: Movie)
}