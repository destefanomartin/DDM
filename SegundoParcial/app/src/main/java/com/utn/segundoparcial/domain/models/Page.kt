package com.utn.segundoparcial.domain.models

import com.utn.segundoparcial.ResultApi

data class Page(
    val page: Int,
    val results: MutableList<ResultApi>,
    val totalResults: Int,
    val totalPages: Int
)