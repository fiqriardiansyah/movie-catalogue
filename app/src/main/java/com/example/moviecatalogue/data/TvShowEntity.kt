package com.example.moviecatalogue.data

import android.graphics.drawable.Drawable

data class TvShowEntity (
    var id: String? = "",
    var title: String? = "",
    var year: String = "",
    var duration: String? = "",
    var score: String? = "",
    var genre: String? = "",
    var description: String? = "",
    var overview: String? = "",
    var creator: String? = "",
    var poster: Drawable? = null
)