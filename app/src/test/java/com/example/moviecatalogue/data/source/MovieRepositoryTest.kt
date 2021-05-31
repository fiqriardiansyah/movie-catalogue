package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeAcademyRepository(remote)

    private val movies = DataDummy.getMovies()
    private val movie = movies[0]
    private val tvShows = DataDummy.getTvShows()
    private val tvShow = tvShows[0]

    @Test
    fun getAllMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback).onAllMovieReceived(movies)
            null
        }.`when`(remote).getMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovie())
        verify(remote).getMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movies.size.toLong(), movieEntities.size.toLong() )
    }

    @Test
    fun getAllTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback).onAllTvShowReceived(tvShows)
            null
        }.`when`(remote).getTvShows(any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getAllTvShow())
        verify(remote).getTvShows(any())
        assertNotNull(tvShowEntities)
        assertEquals(tvShows.size.toLong(), tvShowEntities.size.toLong() )
    }

    @Test
    fun getMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadMovieCallback).onMovieReceived(movie)
            null
        }.`when`(remote).getMovie(eq(movie.id!!), any())

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovie(movie.id!!))

        verify(remote).getMovie(eq(movie.id!!), any())

        assertNotNull(movieEntity)
        assertEquals(movieEntity.id,movie.id)
        assertEquals(movieEntity.title,movie.title)

    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadTvShowCallback).onTvShowReceived(tvShow)
            null
        }.`when`(remote).getTvShow(eq(tvShow.id!!), any())

        val tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getTvShow(tvShow.id!!))

        verify(remote).getTvShow(eq(tvShow.id!!), any())

        assertNotNull(tvShowEntity)
        assertEquals(tvShowEntity.id,tvShow.id)
        assertEquals(tvShowEntity.title,tvShow.title)
    }
}