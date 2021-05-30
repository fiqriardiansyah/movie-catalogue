package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository

class TvShowViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getTvShows(): LiveData<List<TvShowEntity>> = movieRepository.getAllTvShow()
}