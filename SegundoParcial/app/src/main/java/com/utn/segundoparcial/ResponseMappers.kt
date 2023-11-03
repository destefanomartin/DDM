package com.utn.segundoparcial

import com.utn.segundoparcial.domain.models.Genre
import com.utn.segundoparcial.domain.models.Movie
import com.utn.segundoparcial.domain.models.Page

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

fun GenreResponse.toGenre(): Genre = Genre(
    genres = genres
)

fun ResultApi.toDomain(): Movie  {
    val poster = "${"https://image.tmdb.org/t/p/w500"}${this.posterPath}"
    return Movie(adult, backdropPath, genres, id, language, title, overview, popularity, poster, releaseDate, voteAverage, voteCount)
}