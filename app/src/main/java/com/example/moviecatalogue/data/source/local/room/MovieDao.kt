package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity


@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities ")
    fun getMovies(): DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM tvshowentities ")
    fun getTvShows(): DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM movieentities WHERE movieid = :movieId ")
    fun getMovie(movieId: String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId ")
    fun getTvShow(tvShowId: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShow: List<TvShowEntity>)

    @Query("SELECT * FROM movieentities WHERE title LIKE '%'|| :title ||'%' ")
    fun searchMovie(title: String): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE title LIKE '%'|| :title ||'%' ")
    fun searchTvShow(title: String): DataSource.Factory<Int, TvShowEntity>

}