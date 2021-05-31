package com.example.moviecatalogue.ui.main

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
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
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    private var tvShows: ArrayList<TvShowEntity> = DataDummy.getTvShows()
    private var movies: ArrayList<MovieEntity> = DataDummy.getMovies()

    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadTvShows(){
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvShows.size - 1))
    }

    @Test
    fun loadDetailTvShow(){
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(tvShows.size - 1,click()))
        onView(withId(R.id.headerDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText( tvShows[tvShows.size - 1].genre.toString() )))
        onView(withId(R.id.tvOverview)).check(matches(withText( tvShows[tvShows.size - 1].overview.toString() )))
    }

    @Test
    fun loadMovie(){
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movies.size - 1))
    }

    @Test
    fun loadDetailMovie(){
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(movies.size - 1,click()))
        onView(withId(R.id.headerDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText( movies[movies.size - 1].genre.toString() )))
        onView(withId(R.id.tvOverview)).check(matches(withText( movies[movies.size - 1].overview.toString() )))
    }

}