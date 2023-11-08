package com.utn.segundoparcial.data.models

data class User(
    val id : String,
    val email: String,
    val name: String,
    val films: MutableList<MovieDetails>,
    val country: String
) {
    constructor() : this("", "", "", mutableListOf(), "")
}


