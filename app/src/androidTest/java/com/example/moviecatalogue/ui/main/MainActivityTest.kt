package com.example.moviecatalogue.ui.main

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    private var tvShows: ArrayList<TvShowEntity> = DataDummy.getTvShows()
    private var movies: ArrayList<MovieEntity> = DataDummy.getMovies()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadTvShows(){
        delayThreeSecond()
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShows.size - 1))
    }

    @Test
    fun loadDetailTvShow(){
        delayThreeSecond()
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(tvShows.size - 1,click()))
        delayThreeSecond()
        onView(withId(R.id.headerDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText( tvShows[tvShows.size - 1].genre.toString() )))
        onView(withId(R.id.tvOverview)).check(matches(withText( tvShows[tvShows.size - 1].overview.toString() )))
    }

    @Test
    fun loadMovie(){
        delayThreeSecond()
        onView(withText("MOVIE")).perform(click())
        delayThreeSecond()
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movies.size - 1))
    }

    @Test
    fun loadDetailMovie(){
        delayThreeSecond()
        onView(withText("MOVIE")).perform(click())
        delayThreeSecond()
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(movies.size - 1,click()))
        delayThreeSecond()
        onView(withId(R.id.headerDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText( movies[movies.size - 1].genre.toString() )))
        onView(withId(R.id.tvOverview)).check(matches(withText( movies[movies.size - 1].overview.toString() )))
    }


    private fun delayThreeSecond() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}