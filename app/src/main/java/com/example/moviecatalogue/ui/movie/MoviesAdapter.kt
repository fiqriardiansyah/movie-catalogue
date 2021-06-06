package com.example.moviecatalogue.ui.movie

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.*
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.MovieItemBinding
import com.example.moviecatalogue.ui.detail.DetailActivity
import com.example.moviecatalogue.utils.Utils

class MoviesAdapter(val context: Context): RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){

    val listMovies = ArrayList<MovieEntity>()

    fun setData(movies: List<MovieEntity>?){
        if(movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
    }

    inner class ViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity){
            with(binding){

                tvTitleMovie.text = movie.title
                tvCreatorMovie.text = movie.director
                tvDurationMovie.text = movie.duration
                tvGenreTvMovie.text = movie.genre
                tvScoreMovie.text = movie.score
                tvYearMovie.text = movie.year
                tvReleaseMovie.text = movie.release

                var requestOptions = RequestOptions()
                requestOptions = requestOptions.transform(CenterCrop(),RoundedCorners(22)).placeholder(R.drawable.ic_loading)

                val poster = Utils.getPoster("movie",movie.poster,context)

                Glide.with(itemView.context)
                    .load(poster)
                    .apply(requestOptions)
                    .error(R.drawable.ic_error)
                    .into(posterMovie)

                val backgroundLayout = bgStrokeMovie
                val backgroundDrawable = GradientDrawable()
                backgroundDrawable.setStroke(2, Utils.randomColor(itemView.context))
                backgroundDrawable.cornerRadius = 22f
                backgroundDrawable.setColor(Color.WHITE)
                backgroundLayout.setBackgroundDrawable(backgroundDrawable)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TYPE,"movie")
                    intent.putExtra(DetailActivity.EXTRA_ID,movie.movieId)
                    itemView.context.startActivity(intent)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val movieItemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(movieItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    override fun getItemCount(): Int = this.listMovies.size
}