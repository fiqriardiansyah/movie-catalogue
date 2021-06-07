package com.example.moviecatalogue.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.vo.Resource

class TvShowViewModel(private val movieRepository: MovieRepository): ViewModel() {
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = movieRepository.getAllTvShow()
    fun getAllFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> = movieRepository.getAllFavoriteTvShow()
    fun searchTvShow(title: String): LiveData<PagedList<TvShowEntity>> = movieRepository.searchTvShow(title)
    fun searchFavoritesTvShow(title: String): LiveData<PagedList<TvShowEntity>> = movieRepository.searchFavoritesTvShow(title)
}