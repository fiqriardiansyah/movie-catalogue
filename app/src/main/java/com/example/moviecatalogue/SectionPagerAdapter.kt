package com.example.moviecatalogue

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecatalogue.ui.movie.MoviesFragment
import com.example.moviecatalogue.ui.tvshow.TvShowsFragment

class SectionPagerAdapter(private val context: Context,fm: FragmentManager): FragmentPagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private val TAB_TITLE = intArrayOf(R.string.tv_show,R.string.movie)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> TvShowsFragment()
            1 -> MoviesFragment()
            else -> Fragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TAB_TITLE[position])
}