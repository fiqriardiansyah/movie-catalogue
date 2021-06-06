package com.example.moviecatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class,TvShowEntity::class],version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private var instance: FavoriteDatabase? = null

        fun getInstance(context: Context): FavoriteDatabase =
            instance ?: synchronized(this){
                Room.databaseBuilder(context.applicationContext,FavoriteDatabase::class.java,"favorite.db")
                    .build()
                    .apply {
                    instance = this
                }
            }
    }
}