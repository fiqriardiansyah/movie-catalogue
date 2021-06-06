package com.example.moviecatalogue.data.source.local.entity

import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tvshowentities")
data class TvShowEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var tvShowId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "year")
    var year: String = "",

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "score")
    var score: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "creator")
    var creator: String,

    @ColumnInfo(name = "poster")
    var poster: String,

    @ColumnInfo(name = "isfavorite")
    var isFavorite: Boolean,

)