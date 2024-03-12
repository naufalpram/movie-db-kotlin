package com.task.moviedb.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(val id: Int, val title: String, val description: String, val posterResId: Int)
    : Parcelable