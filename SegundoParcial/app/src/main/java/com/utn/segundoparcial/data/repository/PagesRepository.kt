package com.utn.segundoparcial.data.repository

import com.utn.segundoparcial.data.models.Movie
import com.utn.segundoparcial.data.models.MovieDetails

interface PagesRepository {
    suspend fun getPage(page : Int): MutableList<Movie>

    suspend fun getMoviesForPage(page : Int): List<Movie>

    suspend fun getMoviesById(id: Int): MovieDetails?



}