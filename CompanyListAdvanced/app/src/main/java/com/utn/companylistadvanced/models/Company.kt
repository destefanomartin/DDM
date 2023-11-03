package com.utn.companylistadvanced.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(var employees: Int , var id : String, var location: String, var logo : String, var name : String, var zipCode : Int) : Parcelable
{
    constructor() : this(0, "", "", "", "", 0)

    init {
        this.id = id!!
        this.name = name!!
        this.location = location!!
        this.zipCode = zipCode!!
        this.logo = logo!!
        this.employees = employees!!
    }

}

