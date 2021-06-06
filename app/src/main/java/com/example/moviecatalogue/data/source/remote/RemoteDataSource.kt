package com.example.moviecatalogue.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.utils.EspressoIdlingResource
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

    fun getMovies(): LiveData<ApiResponse<List<MovieEntity>>>{
        EspressoIdlingResource.increment()

        val resultMovies = MutableLiveData<ApiResponse<List<MovieEntity>>>()

        handler.postDelayed({
            resultMovies.value = ApiResponse.success(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
            }, SERVICE_LATENCY_IN_MILLIS
        )

        return resultMovies
    }

    fun getTvShows(): LiveData<ApiResponse<List<TvShowEntity>>>{
        EspressoIdlingResource.increment()

        val resultTvShows = MutableLiveData<ApiResponse<List<TvShowEntity>>>()
        handler.postDelayed({
            resultTvShows.value = ApiResponse.success(jsonHelper.loadTvShows())
            EspressoIdlingResource.decrement()
                            }, SERVICE_LATENCY_IN_MILLIS
        )
        return resultTvShows
    }

    fun getTvShow(id: String): LiveData<ApiResponse<TvShowEntity>>{
        EspressoIdlingResource.increment()

        val resultTvShow = MutableLiveData<ApiResponse<TvShowEntity>>()

        handler.postDelayed({
            resultTvShow.value = ApiResponse.success(jsonHelper.loadTvShow(id))
            EspressoIdlingResource.decrement()
            }, SERVICE_LATENCY_IN_MILLIS
        )

        return resultTvShow
    }

    fun getMovie(id: String): LiveData<ApiResponse<MovieEntity>>{
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieEntity>>()

        handler.postDelayed({
                resultMovie.value = ApiResponse.success(jsonHelper.loadMovie(id))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )

        return resultMovie
    }

}