package com.utn.companylistadvanced.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Company(
    val name : String, val location: String, val zipCode : Int, val logo : String, val employees : Int
) : Parcelable
