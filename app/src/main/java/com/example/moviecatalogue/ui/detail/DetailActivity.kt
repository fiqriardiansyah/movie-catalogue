package com.example.moviecatalogue.ui.detail

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.utils.Utils
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

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

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this,factory).get(DetailViewModel::class.java)

        var tvShow = TvShowEntity()
        var movie = MovieEntity()

        val extras = intent.extras
        if(extras != null){
            val type = extras.getString(EXTRA_TYPE).toString()
            val id = extras.getSerializable(EXTRA_ID).toString()
            if(id != null){

                if(type == "tv_show"){

                    binding.progressbar.visibility = View.VISIBLE

                    viewModel.setTvShow(id)
                    viewModel.getTvShow().observe(this , {
                        binding.progressbar.visibility = View.INVISIBLE
                        tvShow = it
                        renderDetail(type,null,it)
                    })
    
                }else{

                    binding.progressbar.visibility = View.VISIBLE

                    viewModel.setMovie(id)
                    viewModel.getMovie().observe(this, {
                        binding.progressbar.visibility = View.INVISIBLE
                        movie = it
                        renderDetail(type,it,null)
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

    private fun renderDetail(type: String,movie: MovieEntity?,tvShow: TvShowEntity?){

        when(type){
            "movie" -> {

                supportActionBar?.setTitle(Html.fromHtml("<font color=\"#606060\">${movie?.title}</font>"))

                Glide.with(this)
                    .load(movie?.poster)
                    .into(binding.posterJumbo)

                var requestOptions = RequestOptions()
                    .transform(CenterCrop(),RoundedCorners(22))
                    .placeholder(R.drawable.ic_loading)

                Glide.with(this)
                    .load(movie?.poster)
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

                Glide.with(this)
                    .load(tvShow?.poster)
                    .into(binding.posterJumbo)

                var requestOptions = RequestOptions()
                    .transform(CenterCrop(),RoundedCorners(22))
                    .placeholder(R.drawable.ic_loading)

                Glide.with(this)
                    .load(tvShow?.poster)
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