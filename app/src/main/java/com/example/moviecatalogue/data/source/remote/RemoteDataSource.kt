package com.example.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper){

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }

    }

    fun getMovies(callback: LoadMoviesCallback){
        handler.postDelayed(
            { callback.onAllMovieReceived(jsonHelper.loadMovies()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getTvShows(callback: LoadTvShowsCallback){
        handler.postDelayed(
            { callback.onAllTvShowReceived(jsonHelper.loadTvShows()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getTvShow(id: String,callback: LoadTvShowCallback){
        handler.postDelayed(
            {callback.onTvShowReceived(jsonHelper.loadTvShow(id))},
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getMovie(id: String,callback: LoadMovieCallback){
        handler.postDelayed(
            {callback.onMovieReceived(jsonHelper.loadMovie(id))},
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadMoviesCallback {
        fun onAllMovieReceived(movieResponse: List<MovieEntity>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowReceived(tvShowResponse: List<TvShowEntity>)
    }

    interface LoadTvShowCallback{
        fun onTvShowReceived(tvShow: TvShowEntity)
    }

    interface LoadMovieCallback{
        fun onMovieReceived(movie: MovieEntity)
    }

}