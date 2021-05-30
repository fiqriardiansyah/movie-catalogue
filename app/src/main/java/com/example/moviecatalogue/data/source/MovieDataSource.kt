package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity

interface MovieDataSource {

    fun getAllMovie(): LiveData<List<MovieEntity>>

    fun getAllTvShow(): LiveData<List<TvShowEntity>>

    fun getMovie(id: String): LiveData<MovieEntity>

    fun getTvShow(id: String): LiveData<TvShowEntity>
}