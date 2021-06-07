package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.room.FavoriteDao
import com.example.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao,private val favoriteDao: FavoriteDao){

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao,favoriteDao: FavoriteDao): LocalDataSource {
            return instance ?: LocalDataSource(movieDao,favoriteDao).apply { instance = this }
        }
    }

    fun getAllMovie() : DataSource.Factory<Int,MovieEntity> = movieDao.getMovies()

    fun getAllTvShow(): DataSource.Factory<Int,TvShowEntity> = movieDao.getTvShows()

    fun getMovie(movieId: String) = movieDao.getMovie(movieId)

    fun getTvShow(tvShowId: String) = movieDao.getTvShow(tvShowId)

    fun insertMovies(movie: List<MovieEntity>) = movieDao.insertMovies(movie)

    fun insertTvShows(tvShow: List<TvShowEntity>) = movieDao.insertTvShows(tvShow)


    fun getAllFavoriteMovie(): DataSource.Factory<Int,MovieEntity> = favoriteDao.getAllMovies()

    fun getAllFavoriteTvShow(): DataSource.Factory<Int,TvShowEntity> = favoriteDao.getAllTvShows()

    fun getFavoriteMovie(movieId: String): LiveData<MovieEntity> = favoriteDao.getMovie(movieId)

    fun getFavoriteTvShow(tvShowId: String): LiveData<TvShowEntity> = favoriteDao.getTvShow(tvShowId)

    fun insertFavoriteMovie(movie: MovieEntity) = favoriteDao.insertMovie(movie)

    fun insertFavoriteTvShow(tvShow: TvShowEntity) = favoriteDao.insertTvShow(tvShow)

    fun deleteFavoriteMovie(movie: MovieEntity) = favoriteDao.deleteMovie(movie)

    fun deleteFavoriteTvShow(tvShow: TvShowEntity) = favoriteDao.deleteTvShow(tvShow)

}