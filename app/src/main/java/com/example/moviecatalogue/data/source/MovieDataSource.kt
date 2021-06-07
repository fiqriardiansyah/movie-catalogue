package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getAllMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getMovie(id: String): LiveData<Resource<MovieEntity>>

    fun getTvShow(id: String): LiveData<Resource<TvShowEntity>>

}