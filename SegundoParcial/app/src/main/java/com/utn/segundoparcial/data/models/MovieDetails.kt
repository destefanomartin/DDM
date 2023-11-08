package com.utn.segundoparcial.data.models

data class MovieDetails(
    val backdropPath: String,
    val movieId : Int,
    val genders: String,
    val homepage: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val runtime: String,
    val voteAverage: Double,
    val voteCount: Double
) {
    constructor() : this("",0, "", "", "", "", "", "", "", 0.0, 0.0)
}
fun MovieDetailsResponse.toDomain(): MovieDetails {
    val backdropPath = if (backdropPath.isNullOrEmpty()) "" else "${"https://image.tmdb.org/t/p/w500"}${this.backdropPath}"
    val movieId = movieId
    val posterPath = "${"https://image.tmdb.org/t/p/w500"}${this.posterPath}"
    val runtime = if (this.runtime == null) "" else "${this.runtime}" + " min"
    val homepage = this.homepage
    val voteAverage = this.voteAverage
    val voteCount = this.voteCount
    val genres = mutableListOf<String>()
    for (i in this.genres) genres.add(i.name)
    val genders = genres.joinToString(separator = ", ").plus(".")

    return MovieDetails(backdropPath, movieId, genders,homepage, title,
        overview, posterPath, releaseDate, runtime, voteAverage , voteCount)
}

fun MovieDetails.toMovie(): Movie {
    val adult = false
    val backdropPath = backdropPath
    val genres = emptyList<Int>()
    val movieId = movieId
    val origLanguage = ""
    val origTitle = title
    val overview = overview
    val popularity = 0.0
    val posterPath = posterPath
    val releaseDate = releaseDate
    val voteAverage = voteAverage
    val voteCount = voteCount.toInt()

    return Movie(adult, backdropPath, genres, movieId, origLanguage, origTitle, overview, popularity, posterPath, releaseDate, voteAverage, voteCount)

}