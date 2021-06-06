package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getTvShows(): LiveData<Resource<List<TvShowEntity>>> = movieRepository.getAllTvShow()
    fun getAllFavoriteTvShow(): LiveData<List<TvShowEntity>> = movieRepository.getAllFavoriteTvShow()
}