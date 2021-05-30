package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository

class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setMovie(id: String){
        movieId = id
    }

    fun setTvShow(id: String){
        tvShowId = id
    }

    fun getMovie(): LiveData<MovieEntity> = movieRepository.getMovie(movieId)
    fun getTvShow(): LiveData<TvShowEntity> = movieRepository.getTvShow(tvShowId)

}