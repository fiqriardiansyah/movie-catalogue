package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieEntity
import com.example.moviecatalogue.data.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var dataDummy: DataDummy
    private lateinit var movie: MovieEntity
    private lateinit var tvShow: TvShowEntity

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Before
    fun setUp(){
        viewModel = DetailViewModel(movieRepository)
        dataDummy = DataDummy
        movie = dataDummy.getMovies()[0]
        tvShow = dataDummy.getTvShows()[0]
    }

    @Test
    fun getMovie() {
        viewModel.setMovie(movie.id!!)

        val mv = MutableLiveData<MovieEntity>()
        mv.value = movie

        `when`(movieRepository.getMovie(movie.id!!)).thenReturn(mv)
        val movieEntity = viewModel.getMovie().value as MovieEntity
        verify(movieRepository).getMovie(movie.id!!)
        assertNotNull(movieEntity)
        assertEquals(movie.id,movieEntity.id)
        assertEquals(movie.title,movieEntity.title)
        assertEquals(movie.year,movieEntity.year)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(movie)
    }

    @Test
    fun getTvShow() {
        viewModel.setTvShow(tvShow.id!!)

        val tv = MutableLiveData<TvShowEntity>()
        tv.value = tvShow

        `when`(movieRepository.getTvShow(tvShow.id!!)).thenReturn(tv)
        val tvShowEntity = viewModel.getTvShow().value as TvShowEntity
        verify(movieRepository).getTvShow(tvShow.id!!)
        assertNotNull(tvShowEntity)
        assertEquals(tvShow.id,tvShowEntity.id)
        assertEquals(tvShow.title,tvShowEntity.title)
        assertEquals(tvShow.year,tvShowEntity.year)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShow)
    }
}