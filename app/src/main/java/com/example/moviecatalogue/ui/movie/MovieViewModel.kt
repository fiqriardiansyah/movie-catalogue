package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.source.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = movieRepository.getAllMovie()
}