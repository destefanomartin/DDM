package com.utn.segundoparcial.domain.repository

import com.google.android.gms.common.api.Response
import com.utn.segundoparcial.MoviesResponse
import com.utn.segundoparcial.PageResponse
import com.utn.segundoparcial.domain.models.Movie
import com.utn.segundoparcial.domain.models.Page
import retrofit2.http.Query

interface PagesRepository {
    suspend fun getPage(page : Int): MutableList<Movie>?

    suspend fun getMoviesForPage(page : Int): List<Movie>

    suspend fun getMoviesById(id: Int): Movie?



}