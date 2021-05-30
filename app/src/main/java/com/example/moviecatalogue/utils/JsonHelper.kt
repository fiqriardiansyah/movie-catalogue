package com.example.moviecatalogue.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(val context: Context) {

    private fun parsingFileToString(fileName: String): String?{
        return try{
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        }catch (e: IOException){
            e.printStackTrace()
            null
        }
    }

    private fun getPoster(type: String,name: String): Drawable{
        val inputStream = context.assets.open("$type/$name")
        val drawable = Drawable.createFromStream(inputStream,null)
        return drawable
    }

    fun loadMovies(): List<MovieEntity>{
        val list = ArrayList<MovieEntity>()

        try{
            val responseJson = JSONObject(parsingFileToString("data.json").toString())
            val listMovies = responseJson.getJSONArray("movies")

            for(i in 0 until listMovies.length()){
                val movie = listMovies.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val year = movie.getString("year")
                val score = movie.getString("score")
                val duration = movie.getString("duration")
                val release = movie.getString("release")
                val genre = movie.getString("genre")
                val overview = movie.getString("overview")
                val director = movie.getString("director")
                val poster = movie.getString("poster")

                val posterDrawable = getPoster("movie",poster)
                val mv = MovieEntity(id,title,year,duration,release,score,genre,overview,director,posterDrawable)
                list.add(mv)
            }

        }catch (e: JSONException){
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShows(): List<TvShowEntity>{
        val list = ArrayList<TvShowEntity>()

        try{
            val responseJson = JSONObject(parsingFileToString("data.json").toString())
            val listMovies = responseJson.getJSONArray("tv_shows")

            for(i in 0 until listMovies.length()){
                val movie = listMovies.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val year = movie.getString("year")
                val score = movie.getString("score")
                val duration = movie.getString("duration")
                val description = movie.getString("description")
                val genre = movie.getString("genre")
                val overview = movie.getString("overview")
                val creator = movie.getString("creator")
                val poster = movie.getString("poster")

                val posterDrawable = getPoster("tvshow",poster)
                val mv = TvShowEntity(id,title,year,duration,score,genre,description,overview,creator,posterDrawable)
                list.add(mv)
            }

        }catch (e: JSONException){
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShow(id: String): TvShowEntity{
        val listTvShow = loadTvShows()
        return listTvShow.find{ it.id == id } as TvShowEntity
    }

    fun loadMovie(id: String): MovieEntity {
        val listMovie = loadMovies()
        return listMovie.find { it.id == id } as MovieEntity
    }

}