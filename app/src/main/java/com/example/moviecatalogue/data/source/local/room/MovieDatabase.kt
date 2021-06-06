package com.example.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class,TvShowEntity::class],version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase{
            return instance ?: synchronized(this){
                Room.databaseBuilder(context.applicationContext,MovieDatabase::class.java,"movie.db")
                    .build().apply {
                        instance = this
                    }
            }
        }

    }

}