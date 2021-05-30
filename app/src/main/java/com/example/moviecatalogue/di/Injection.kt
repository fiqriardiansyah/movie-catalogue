package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.JsonHelper

object Injection {
     fun provideRepository(context: Context): MovieRepository{
         val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
         return MovieRepository.getInstance(remoteDataSource)
     }
}