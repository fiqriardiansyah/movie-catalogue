package com.example.moviecatalogue.ui.favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.moviecatalogue.R
import com.example.moviecatalogue.SectionPagerAdapter
import com.example.moviecatalogue.databinding.ActivityFavoritesBinding
import com.example.moviecatalogue.ui.movie.MoviesFragment

class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f
        supportActionBar?.setTitle(Html.fromHtml("<font color=\"#606060\">FAVORITES</font>"))

        val sectionPagerAdapter = SectionPagerAdapter(this,supportFragmentManager)
        binding.viewPager.adapter = sectionPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }
}