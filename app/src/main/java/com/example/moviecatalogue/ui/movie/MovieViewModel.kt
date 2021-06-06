package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getMovies(): LiveData<Resource<List<MovieEntity>>> = movieRepository.getAllMovie()
    fun getAllFavoriteMovies(): LiveData<List<MovieEntity>> = movieRepository.getAllFavoriteMovie()
}