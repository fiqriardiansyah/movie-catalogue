package com.example.moviecatalogue.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.moviecatalogue.R
import com.example.moviecatalogue.SectionPagerAdapter
import com.example.moviecatalogue.databinding.ActivityMainBinding

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
}