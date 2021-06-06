package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository): ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvShowId: String

    fun setMovie(id: String){
        movieId = id
    }

    fun setTvShow(id: String){
        tvShowId = id
    }

    fun getMovie(): LiveData<Resource<MovieEntity>> = movieRepository.getMovie(movieId)
    fun getTvShow(): LiveData<Resource<TvShowEntity>> = movieRepository.getTvShow(tvShowId)

    fun getFavoriteMovie(): LiveData<MovieEntity> = movieRepository.getFavoriteMovie(movieId)
    fun getFavoriteTvShow(): LiveData<TvShowEntity> = movieRepository.getFavoriteTvShow(tvShowId)

}