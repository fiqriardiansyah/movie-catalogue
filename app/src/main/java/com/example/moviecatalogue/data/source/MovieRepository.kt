package com.example.moviecatalogue.data.source

import android.graphics.Movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource): MovieDataSource {

    companion object {

        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this){
                instance ?: MovieRepository(remoteData).apply { instance = this }
            }

    }


    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        val moviesResult = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getMovies(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMovieReceived(movies: List<MovieEntity>) {
                moviesResult.postValue(movies)
            }
        })
        return moviesResult
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {

        val tvShowsResult = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getTvShows(object : RemoteDataSource.LoadTvShowsCallback{
            override fun onAllTvShowReceived(tvShowResponse: List<TvShowEntity>) {
                tvShowsResult.postValue(tvShowResponse)
            }
        })
        return tvShowsResult
    }

    override fun getMovie(id: String): LiveData<MovieEntity> {
        var movie = MutableLiveData<MovieEntity>()
        remoteDataSource.getMovie(id,object :RemoteDataSource.LoadMovieCallback{
            override fun onMovieReceived(mv: MovieEntity) {
                movie.postValue(mv)
            }
        })

        return movie
    }

    override fun getTvShow(id: String): LiveData<TvShowEntity> {
        var tvShow = MutableLiveData<TvShowEntity>()
        remoteDataSource.getTvShow(id,object :RemoteDataSource.LoadTvShowCallback{
            override fun onTvShowReceived(tv: TvShowEntity) {
                tvShow.postValue(tv)
            }
        })

        return tvShow
    }


}