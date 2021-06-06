package com.example.moviecatalogue.data

import android.graphics.drawable.Drawable

data class MovieEntity (
    var id: String? = "",
    var title: String? = "",
    var year: String = "",
    var duration: String? = "",
    var release: String? = "",
    var score: String? = "",
    var genre: String? = "",
    var overview: String? = "",
    var director: String? = "",
    var poster: String? = "",
    var isFavorite: Boolean = false
)