package com.example.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.moviecatalogue.R
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest{

    private var tvShows = DataDummy.getTvShows()
    private var movies = DataDummy.getMovies()

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
    }

    @Test
    fun loadDetailTvShow(){
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.headerDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText( tvShows[0].genre )))
        onView(withId(R.id.tvOverview)).check(matches(withText( tvShows[0].overview )))
    }

    @Test
    fun loadMovie(){
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailMovie(){
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.headerDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText( movies[0].genre )))
        onView(withId(R.id.tvOverview)).check(matches(withText( movies[0].overview )))
    }


    // sebelum menjalankan test ini pastikan tidak ada movie yang tersimpan di database favorite
    @Test
    fun loadAddDeleteFavoritesMovie(){

        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        // add favorite
        onView(withId(R.id.fabFavorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorites)).perform(click())
        onView(withText("MOVIE")).perform(click())
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.headerDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText( movies[0].genre )))
        onView(withId(R.id.tvOverview)).check(matches(withText( movies[0].overview )))
//        delete favorite
        onView(withId(R.id.fabFavorite)).perform(click())
    }


    // sebelum menjalankan test ini pastikan tidak ada tvshow yang tersimpan di database favorite
    @Test
    fun loadAddDeleteFavoritesTvShow(){

        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))

        //  add favorite
        onView(withId(R.id.fabFavorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.favorites)).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
        onView(withId(R.id.headerDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvGenre)).check(matches(withText( tvShows[0].genre )))
        onView(withId(R.id.tvOverview)).check(matches(withText( tvShows[0].overview )))
        //  delete favorite
        onView(withId(R.id.fabFavorite)).perform(click())
    }

}