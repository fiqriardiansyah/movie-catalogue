package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.FavoriteDatabase
import com.example.moviecatalogue.data.source.local.room.MovieDatabase
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.JsonHelper

object Injection {
     fun provideRepository(context: Context): MovieRepository{

         val databaseMovie = MovieDatabase.getInstance(context)
         val databaseFavorite = FavoriteDatabase.getInstance(context)

         val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
         val localDataSource = LocalDataSource.getInstance(databaseMovie.movieDao(),databaseFavorite.favoriteDao())
         val appExecutors = AppExecutors()

         return MovieRepository.getInstance(remoteDataSource,localDataSource,appExecutors)
     }
}