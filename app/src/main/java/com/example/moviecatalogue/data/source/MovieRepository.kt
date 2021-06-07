package com.example.moviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
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

    override fun getAllMovie(): LiveData<Resource<PagedList<MovieEntityDb>>> {

       return object : NetworkBoundResource<PagedList<MovieEntityDb>,List<MovieEntity>>(appExecutors){
           override fun loadFromDB(): LiveData<PagedList<MovieEntityDb>> {
               val config = PagedList.Config.Builder()
                   .setEnablePlaceholders(false)
                   .setInitialLoadSizeHint(4)
                   .setPageSize(4)
                   .build()

               return LivePagedListBuilder(localDataSource.getAllMovie(),config).build()

           }

           override fun shouldFetch(data: PagedList<MovieEntityDb>?): Boolean {
               return data == null || data.isEmpty()
           }

           override fun createCall(): LiveData<ApiResponse<List<MovieEntity>>> {
               return remoteDataSource.getMovies()
           }

           override fun saveCallResult(data: List<MovieEntity>) {
               val movieList = ArrayList<MovieEntityDb>()

               for (response in data){
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
                       response.poster!!
                   )
                   movieList.add(movie)
               }

               localDataSource.insertMovies(movieList)
           }

       }.asLiveData()
    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntityDb>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntityDb>,List<TvShowEntity>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowEntityDb>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(localDataSource.getAllTvShow(),config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntityDb>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowEntity>>> {
                return remoteDataSource.getTvShows()
            }

            override fun saveCallResult(data: List<TvShowEntity>) {
                val tvShows = ArrayList<TvShowEntityDb>()

                for (response in data){
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
                        response.poster!!
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

    fun getAllFavoriteMovie(): LiveData<PagedList<MovieEntityDb>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getAllFavoriteMovie(),config).build()
    }

    fun getAllFavoriteTvShow(): LiveData<PagedList<TvShowEntityDb>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getAllFavoriteTvShow(),config).build()
    }

    fun getFavoriteMovie(id: String): LiveData<MovieEntityDb> = localDataSource.getFavoriteMovie(id)

    fun getFavoriteTvShow(id: String): LiveData<TvShowEntityDb> = localDataSource.getFavoriteTvShow(id)

    fun searchMovie(title: String): LiveData<PagedList<MovieEntityDb>> {
        return LivePagedListBuilder(localDataSource.searchMovie(title),4).build()
    }
    fun searchTvShow(title: String): LiveData<PagedList<TvShowEntityDb>> {
        return LivePagedListBuilder(localDataSource.searchTvShow(title),4).build()
    }

    fun searchFavoritesMovie(title: String): LiveData<PagedList<MovieEntityDb>> {
        return LivePagedListBuilder(localDataSource.searchFavoritesMovie(title),4).build()
    }

    fun searchFavoritesTvShow(title: String): LiveData<PagedList<TvShowEntityDb>> {
        return LivePagedListBuilder(localDataSource.searchFavoritesTvShow(title),4).build()
    }

}