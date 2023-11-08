package com.utn.segundoparcial.data.remote

import com.utn.segundoparcial.data.models.Movie
import com.utn.segundoparcial.data.models.Page
import com.utn.segundoparcial.data.remote.responses.MoviesResponse
import com.utn.segundoparcial.data.remote.responses.PageResponse
import com.utn.segundoparcial.data.remote.responses.ResultApi

fun PageResponse.toPage(): Page = Page(
    page = page,
    results = results,
    totalPages = totalPages,
    totalResults = totalResults
)

fun MoviesResponse.toMovie(): Movie = Movie(
    adult = adult,
    backdropPath = backdropPath,
    genres = genres,
    movieId = id,
    origLanguage = language,
    origTitle = title,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    voteCount = voteCount
)


fun ResultApi.toResults(): Movie {
    val poster = "${"https://image.tmdb.org/t/p/w500"}${this.posterPath}"
    return Movie(adult, backdropPath, genres, id, language, title, overview, popularity, poster, releaseDate, voteAverage, voteCount)
}


