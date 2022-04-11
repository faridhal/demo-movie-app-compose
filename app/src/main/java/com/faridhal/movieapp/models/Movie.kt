package com.faridhal.movieapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val imageURL: String = "",
    val rating: Double = 0.0
): Parcelable