package com.utn.segundoparcial.data.models

import com.utn.segundoparcial.data.remote.responses.ResultApi

data class Page(
    val page: Int,
    val results: MutableList<ResultApi>,
    val totalResults: Int,
    val totalPages: Int
)