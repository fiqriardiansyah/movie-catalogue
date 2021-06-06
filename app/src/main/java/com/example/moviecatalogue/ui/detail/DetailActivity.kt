package com.example.moviecatalogue.ui.detail

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.room.FavoriteDatabase
import com.example.moviecatalogue.data.source.local.room.MovieDao
import com.example.moviecatalogue.data.source.local.room.MovieDatabase
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.ui.favorites.FavoritesActivity
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.Utils
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var tvShow: TvShowEntity
    private lateinit var movie: MovieEntity
    private lateinit var appExecutors: AppExecutors
    private lateinit var favoriteDatabase: FavoriteDatabase

    private lateinit var viewModel: DetailViewModel

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_backspace)

        favoriteDatabase = FavoriteDatabase.getInstance(this)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory).get(DetailViewModel::class.java)

        appExecutors = AppExecutors()

        val extras = intent.extras
        if(extras != null){
            val type = extras.getString(EXTRA_TYPE).toString()
            val id = extras.getSerializable(EXTRA_ID).toString()
            if(id != null){

                if(type == "tv_show"){

                    binding.progressbar.visibility = View.VISIBLE

                    viewModel.setTvShow(id)
                    viewModel.getTvShow().observe(this , { tv ->
                        if(tv != null){
                            when(tv.status){
                                Status.LOADING -> binding.progressbar.visibility = View.VISIBLE
                                Status.SUCCESS -> {
                                    binding.progressbar.visibility = View.GONE
                                    tvShow = tv.data!!
                                    renderDetail(type,null,tvShow)
                                    fabFavorite(null,tv.data)
                                }
                                Status.ERROR -> {
                                    binding.progressbar.visibility = View.VISIBLE
                                    Toast.makeText(applicationContext,"gagal menampilkan detail",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
    
                }else{

                    binding.progressbar.visibility = View.VISIBLE

                    viewModel.setMovie(id)
                    viewModel.getMovie().observe(this, { mv ->
                        if(mv != null){
                            when(mv.status){
                                Status.LOADING -> binding.progressbar.visibility = View.VISIBLE
                                Status.SUCCESS -> {
                                    binding.progressbar.visibility = View.GONE
                                    movie = mv.data!!
                                    renderDetail(type,movie,null)
                                    fabFavorite(mv.data,null)
                                }
                                Status.ERROR -> {
                                    binding.progressbar.visibility = View.VISIBLE
                                    Toast.makeText(applicationContext,"gagal menampilkan detail",Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })
                }

                binding.fabShare.setOnClickListener{
                    ShareCompat.IntentBuilder
                        .from(this)
                        .setType("text/plain")
                        .setChooserTitle("bagikan film")
                        .setText(if(type == "tv_show") tvShow.title else movie.title )
                        .startChooser()
                }

            }
        }

    }

    private fun fabFavorite(movieEntity: MovieEntity?,tvShowEntity: TvShowEntity?){

        if(movieEntity != null){
            viewModel.getFavoriteMovie().observe(this,{ mv ->
                binding.fabFavorite.setImageResource( if(mv != null) R.drawable.ic_favorite_full else R.drawable.ic_favorite_border)

                binding.fabFavorite.setOnClickListener {
                    appExecutors.diskIO().execute {
                        if(mv != null){
                            favoriteDatabase.favoriteDao().deleteMovie(mv)
                        }else{
                            favoriteDatabase.favoriteDao().insertMovie(movie)
                        }
                    }
                }
            })
        }else{
            viewModel.getFavoriteTvShow().observe(this , { tv ->
                binding.fabFavorite.setImageResource( if(tv != null) R.drawable.ic_favorite_full else R.drawable.ic_favorite_border)

                binding.fabFavorite.setOnClickListener {
                    appExecutors.diskIO().execute {
                        if(tv != null){
                            favoriteDatabase.favoriteDao().deleteTvShow(tv)
                        }else{
                            favoriteDatabase.favoriteDao().insertTvShow(tvShow)
                        }
                    }
                }
            })
        }

    }

    private fun renderDetail(type: String,movie: MovieEntity?,tvShow: TvShowEntity?){

        when(type){
            "movie" -> {

                supportActionBar?.setTitle(Html.fromHtml("<font color=\"#606060\">${movie?.title}</font>"))

                val poster = Utils.getPoster("movie",movie!!.poster,applicationContext)

                Glide.with(this)
                    .load(poster)
                    .into(binding.posterJumbo)

                val requestOptions = RequestOptions()
                    .transform(CenterCrop(),RoundedCorners(22))
                    .placeholder(R.drawable.ic_loading)

                Glide.with(this)
                    .load(poster)
                    .apply(requestOptions)
                    .error(R.drawable.ic_error)
                    .into(binding.headerDetail.poster)

                val backgroundEffect = binding.posterJumboEffect
                val drawable = GradientDrawable()
                drawable.setColor(Utils.randomColor(this))
                backgroundEffect.setBackgroundDrawable(drawable)
                backgroundEffect.background.alpha = 100

                with(binding.headerDetail){
                    this.tvTitle.text = movie?.title
                    this.tvCreator.text = movie?.director
                    this.tvDuration.text = movie?.duration
                    this.tvScore.text = movie?.score
                    this.tvYear.text = movie?.year
                }
                binding.tvGenre.text = movie?.genre
                binding.tvDescription.text = "-"
                binding.tvOverview.text = movie?.overview
                binding.tvRelease.text = movie?.release
            }
            "tv_show" -> {

                supportActionBar?.setTitle(Html.fromHtml("<font color=\"#606060\">${tvShow?.title}</font>"))

                val poster = Utils.getPoster("tvshow",tvShow!!.poster,applicationContext)

                Glide.with(this)
                    .load(poster)
                    .into(binding.posterJumbo)

                var requestOptions = RequestOptions()
                    .transform(CenterCrop(),RoundedCorners(22))
                    .placeholder(R.drawable.ic_loading)

                Glide.with(this)
                    .load(poster)
                    .apply(requestOptions)
                    .error(R.drawable.ic_error)
                    .into(binding.headerDetail.poster)

                val backgroundEffect = binding.posterJumboEffect
                val drawable = GradientDrawable()
                drawable.setColor(Utils.randomColor(this))
                backgroundEffect.setBackgroundDrawable(drawable)
                backgroundEffect.background.alpha = 100

                with(binding.headerDetail){
                    this.tvTitle.text = tvShow?.title
                    this.tvCreator.text = tvShow?.creator
                    this.tvDuration.text = tvShow?.duration
                    this.tvScore.text = tvShow?.score
                    this.tvYear.text = tvShow?.year
                }
                binding.tvGenre.text = tvShow?.genre
                binding.tvDescription.text = tvShow?.description
                binding.tvOverview.text = tvShow?.overview
                binding.tvRelease.text = "-"
            }
        }
    }

}