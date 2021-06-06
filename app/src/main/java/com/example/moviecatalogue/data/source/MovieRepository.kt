package com.example.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.data.source.local.entity.MovieEntity as MovieEntityDb
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity as TvShowEntityDb
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

class MovieRepository private constructor(

    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors

    ): MovieDataSource {

    companion object {

        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteData: RemoteDataSource,localData: LocalDataSource,appExecutors: AppExecutors): MovieRepository =
            instance ?: synchronized(this){
                instance ?: MovieRepository(remoteData,localData,appExecutors).apply { instance = this }
            }
    }

    override fun getAllMovie(): LiveData<Resource<List<MovieEntityDb>>> {

       return object : NetworkBoundResource<List<MovieEntityDb>,List<MovieEntity>>(appExecutors){
           override fun loadFromDB(): LiveData<List<MovieEntityDb>> {
               return localDataSource.getAllMovie()
           }

           override fun shouldFetch(data: List<MovieEntityDb>?): Boolean {
               return data == null || data.isEmpty()
           }

           override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> {
               return remoteDataSource.getMovies()
           }

           override fun saveCallResult(data: List<MovieEntity>) {
               val movieList = ArrayList<MovieEntityDb>()

               for (response in data){
                   Log.d("Movie","${response.title}")
                   val movie = MovieEntityDb(
                       response.id!!,
                       response.title!!,
                       response.year!!,
                       response.duration!!,
                       response.release!!,
                       response.score!!,
                       response.genre!!,
                       response.overview!!,
                       response.director!!,
                       response.poster!!,
                       response.isFavorite!!
                   )
                   movieList.add(movie)
               }

               localDataSource.insertMovies(movieList)
           }

       }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<List<TvShowEntityDb>>> {
        return object : NetworkBoundResource<List<TvShowEntityDb>,List<TvShowEntity>>(appExecutors){
            override fun loadFromDB(): LiveData<List<TvShowEntityDb>> {
                Log.d("load","LOAD TV")
                return localDataSource.getAllTvShow()
            }

            override fun shouldFetch(data: List<TvShowEntityDb>?): Boolean {
                Log.d("shoudfetch",(data == null || data.isEmpty()).toString())
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowEntity>>> {
                Log.d("createCall","create TV")
                return remoteDataSource.getTvShows()
            }

            override fun saveCallResult(data: List<TvShowEntity>) {
                Log.d("saveCallREsult","saveCall TV")
                val tvShows = ArrayList<TvShowEntityDb>()

                for (response in data){
                    Log.d("TvSHOW","${response.title}")
                    val tvSHow = TvShowEntityDb(
                        response.id!!,
                        response.title!!,
                        response.year!!,
                        response.duration!!,
                        response.score!!,
                        response.genre!!,
                        response.description!!,
                        response.overview!!,
                        response.creator!!,
                        response.poster!!,
                        response.isFavorite!!
                    )
                    tvShows.add(tvSHow)
                }

                localDataSource.insertTvShows(tvShows)
            }

        }.asLiveData()
    }

    override fun getMovie(id: String): LiveData<Resource<MovieEntityDb>> {
        return object: NetworkBoundResource<MovieEntityDb,MovieEntity>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntityDb> {
                return localDataSource.getMovie(id)
            }

            override fun shouldFetch(data: MovieEntityDb?): Boolean {
                return data?.movieId == null
            }

            override fun createCall(): LiveData<ApiResponse<MovieEntity>> {
                return remoteDataSource.getMovie(id)
            }

            override fun saveCallResult(data: MovieEntity) {

            }

        }.asLiveData()
    }

    override fun getTvShow(id: String): LiveData<Resource<TvShowEntityDb>> {
        return object: NetworkBoundResource<TvShowEntityDb,TvShowEntity>(appExecutors){
            override fun loadFromDB(): LiveData<TvShowEntityDb> {
                return localDataSource.getTvShow(id)
            }

            override fun createCall(): LiveData<ApiResponse<TvShowEntity>> {
                return remoteDataSource.getTvShow(id)
            }

            override fun saveCallResult(data: TvShowEntity) {

            }

            override fun shouldFetch(data: TvShowEntityDb?): Boolean {
                return data?.tvShowId == null
            }

        }.asLiveData()
    }

}