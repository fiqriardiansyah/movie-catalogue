package com.example.moviecatalogue.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.databinding.TvShowItemBinding
import com.example.moviecatalogue.ui.detail.DetailActivity

class TvShowAdapter: RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    val listTvShow = ArrayList<TvShowEntity>()

    fun setData(tvShows: List<TvShowEntity>?){
        if(tvShows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShows)
    }

    inner class ViewHolder(private val binding: TvShowItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity){
            with(binding){

                tvTitleTvShow.text = tvShow.title
                tvYearTvShow.text = tvShow.year
                tvDurationTvShow.text = tvShow.duration
                tvScoreTvShow.text = tvShow.score
                tvGenreTvShow.text = itemView.context.resources.getString(R.string.genre,tvShow.genre)
                tvCreatorTvShow.text = itemView.context.resources.getString(R.string.creator,tvShow.creator)

                var requestOptions = RequestOptions()
                requestOptions = requestOptions.transform(CenterCrop(),RoundedCorners(22)).placeholder(R.drawable.ic_loading)

                Glide.with(itemView.context)
                    .load(tvShow.poster)
                    .apply(requestOptions)
                    .error(R.drawable.ic_error)
                    .into(posterTvShow)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context,DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID,tvShow.tvShowId)
                    intent.putExtra(DetailActivity.EXTRA_TYPE,"tv_show")
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.ViewHolder {
        val tvShowItemBinding = TvShowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(tvShowItemBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.ViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size
}