package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.example.moviecatalogue.utils.PagedListUtil
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote,local,appExecutors)

    private val movies = DataDummy.getMovies()
    private val movie = movies[0]
    private val tvShows = DataDummy.getTvShows()
    private val tvShow = tvShows[0]

    @Test
    fun getAllMovie() {

        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getAllMovie()).thenReturn(dataSourceFactory)
        movieRepository.getAllMovie()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getMovies()))
        verify(local).getAllMovie()
        assertNotNull(movieEntities.data)
        assertEquals(movies.size.toLong(), movieEntities.data?.size?.toLong() )
    }

    @Test
    fun getAllTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvShowEntity>
        `when`(local.getAllTvShow()).thenReturn(dataSourceFactory)
        movieRepository.getAllTvShow()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.getTvShows()))
        verify(local).getAllTvShow()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShows.size.toLong(), tvShowEntities.data?.size?.toLong() )
    }

    @Test
    fun getMovie() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.getMovies()[0]
        `when`(local.getMovie(movie.movieId)).thenReturn(dummyEntity)

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getMovie(movie.movieId))
        verify(local).getMovie(movie.movieId)

        assertNotNull(movieEntity)
        assertEquals(movieEntity.data?.movieId,movie.movieId)
        assertEquals(movieEntity.data?.title,movie.title)
    }
//
    @Test
    fun getTvShow() {
        val dummyEntity = MutableLiveData<TvShowEntity>()
        dummyEntity.value = DataDummy.getTvShows()[0]
        `when`(local.getTvShow(tvShow.tvShowId)).thenReturn(dummyEntity)

        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getTvShow(tvShow.tvShowId))
        verify(local).getTvShow(tvShow.tvShowId)

        assertNotNull(tvShowEntities)
        assertEquals(tvShowEntities.data?.tvShowId,tvShow.tvShowId)
        assertEquals(tvShowEntities.data?.title,tvShow.title)
    }

    @Test
    fun getAllFavoriteMovie(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,MovieEntity>
        `when`(local.getAllFavoriteMovie()).thenReturn(dataSourceFactory)
        movieRepository.getAllFavoriteMovie()

        val movieEntities = PagedListUtil.mockPagedList(DataDummy.getMovies())
        verify(local).getAllFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(movies.size.toLong(),movieEntities.size.toLong())
    }

    @Test
    fun getAllFavoriteTvShow(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int,TvShowEntity>
        `when`(local.getAllFavoriteTvShow()).thenReturn(dataSourceFactory)
        movieRepository.getAllFavoriteTvShow()

        val tvShowEntities = PagedListUtil.mockPagedList(DataDummy.getTvShows())
        verify(local).getAllFavoriteTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(tvShows.size.toLong(),tvShowEntities.size.toLong())
    }

    @Test
    fun getFavoriteMovie(){
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.getMovies()[0]
        `when`(local.getFavoriteMovie(movie.movieId)).thenReturn(dummyEntity)

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getFavoriteMovie(movie.movieId))
        verify(local).getFavoriteMovie(movie.movieId)
        assertNotNull(movieEntity)
        assertEquals(movieEntity.movieId,movie.movieId)
        assertEquals(movieEntity.title,movie.title)
    }

    @Test
    fun getFavoriteTvShow(){
        val dummyEntity = MutableLiveData<TvShowEntity>()
        dummyEntity.value = DataDummy.getTvShows()[0]
        `when`(local.getFavoriteTvShow(tvShow.tvShowId)).thenReturn(dummyEntity)

        val tvShowEntity = LiveDataTestUtil.getValue(movieRepository.getFavoriteTvShow(tvShow.tvShowId))
        verify(local).getFavoriteTvShow(tvShow.tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(tvShowEntity.tvShowId,tvShow.tvShowId)
        assertEquals(tvShowEntity.title,tvShow.title)
    }
}