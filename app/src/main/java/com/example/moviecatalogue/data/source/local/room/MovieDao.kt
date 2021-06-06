package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity


@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM movieentities WHERE isfavorite = ${true} ")
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentities WHERE isfavorite = ${true} ")
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM movieentities WHERE movieid = :movieId ")
    fun getMovie(movieId: String): LiveData<MovieEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId ")
    fun getTvShow(tvShowId: String): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShow: List<TvShowEntity>)

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Delete
    fun deleteTvShow(tvShow: TvShowEntity)

}