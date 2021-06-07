package com.example.moviecatalogue.ui.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
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
    private lateinit var observerResource: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setUp(){
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(15)
        val tvShow = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShow.value = dummyTvShows

        Mockito.`when`(movieRepository.getAllTvShow()).thenReturn(tvShow)
        val movieEntities = viewModel.getTvShows().value
        verify(movieRepository).getAllTvShow()
        assertNotNull(movieEntities)
        assertEquals(15,movieEntities?.data?.size)

        viewModel.getTvShows().observeForever(observerResource)
        verify(observerResource).onChanged(dummyTvShows)
    }

    @Test
    fun getAllFavoriteTvShow(){
        val dummyTvShow = pagedList
        `when`(dummyTvShow.size).thenReturn(0)
        val tvShow = MutableLiveData<PagedList<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(movieRepository.getAllFavoriteTvShow()).thenReturn(tvShow)
        val tvShowEntities = viewModel.getAllFavoriteTvShow().value
        verify(movieRepository).getAllFavoriteTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(0,tvShowEntities?.size)

        viewModel.getAllFavoriteTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

}