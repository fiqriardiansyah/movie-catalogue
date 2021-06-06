package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao){

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource {
            return instance ?: LocalDataSource(movieDao).apply { instance = this }
        }
    }

    fun getAllMovie() : LiveData<List<MovieEntity>> = movieDao.getMovies()

    fun getAllTvShow(): LiveData<List<TvShowEntity>> = movieDao.getTvShows()

    fun getFavoritesMovie(): LiveData<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun getFavoriteTvShow(): LiveData<List<TvShowEntity>> = movieDao.getFavoriteTvShows()

    fun getMovie(movieId: String) = movieDao.getMovie(movieId)

    fun getTvShow(tvShowId: String) = movieDao.getTvShow(tvShowId)

    fun insertMovies(movie: List<MovieEntity>) = movieDao.insertMovies(movie)

    fun insertTvShows(tvShow: List<TvShowEntity>) = movieDao.insertTvShows(tvShow)

    fun deleteMovie(movie: MovieEntity) = movieDao.deleteMovie(movie)

    fun deleteTvShow(tvShow: TvShowEntity) = movieDao.deleteTvShow(tvShow)


}