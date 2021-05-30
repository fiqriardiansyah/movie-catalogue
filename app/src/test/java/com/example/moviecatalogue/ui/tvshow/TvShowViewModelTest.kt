package com.example.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShows() {
        val dummyMovies = DataDummy.getTvShows()
        val movies = MutableLiveData<List<TvShowEntity>>()
        movies.value = dummyMovies

        `when`(movieRepository.getAllTvShow()).thenReturn(movies)
        val movieEntities = viewModel.getTvShows().value
        verify(movieRepository).getAllTvShow()
        assertNotNull(movieEntities)
        assertEquals(15,movieEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }


}