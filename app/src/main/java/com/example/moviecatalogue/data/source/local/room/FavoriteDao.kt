package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM movieentities")
    fun getAllMovies(): DataSource.Factory<Int,MovieEntity>

    @Query("SELECT * FROM tvshowentities")
    fun getAllTvShows(): DataSource.Factory<Int,TvShowEntity>

    @Query("SELECT * FROM movieentities WHERE movieid = :movieId")
    fun getMovie(movieId: String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId")
    fun getTvShow(tvShowId: String): LiveData<TvShowEntity>

    @Insert
    fun insertMovie(movie: MovieEntity)

    @Insert
    fun insertTvShow(tvShow: TvShowEntity)

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Delete
    fun deleteTvShow(tvShow: TvShowEntity)

}