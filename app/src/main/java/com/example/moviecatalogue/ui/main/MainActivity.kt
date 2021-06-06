package com.example.moviecatalogue.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import com.example.moviecatalogue.R
import com.example.moviecatalogue.SectionPagerAdapter
import com.example.moviecatalogue.databinding.ActivityMainBinding
import com.example.moviecatalogue.ui.favorites.FavoritesActivity
import com.example.moviecatalogue.ui.movie.MoviesFragment
import com.example.moviecatalogue.ui.tvshow.TvShowsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.elevation = 0f
        supportActionBar?.setTitle(Html.fromHtml("<font color=\"#606060\">MOVIES</font>"))

        val sectionsPagerAdapter = SectionPagerAdapter(this,supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorites -> {
                val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}