package com.example.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.source.FakeMovieRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.MovieRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
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
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private lateinit var dataDummy: DataDummy
    private lateinit var movie: Resource<MovieEntity>
    private lateinit var tvShow: Resource<TvShowEntity>

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private var fakeRepository = FakeMovieRepository(remote,local,appExecutors)

    @Mock
    private lateinit var movieObserverResource: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserverResource: Observer<Resource<TvShowEntity>>

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @Before
    fun setUp(){
        viewModel = DetailViewModel(movieRepository)
        dataDummy = DataDummy
        movie = Resource.success(dataDummy.getMovies()[0])
        tvShow = Resource.success(dataDummy.getTvShows()[0])
    }

    @Test
    fun getMovie() {
        viewModel.setMovie(movie.data?.movieId!!)

        val mv = MutableLiveData<Resource<MovieEntity>>()
        mv.value = movie

        `when`(movieRepository.getMovie(movie.data?.movieId!!)).thenReturn(mv)
        val movieEntity = viewModel.getMovie().value?.data as MovieEntity
        verify(movieRepository).getMovie(movie.data?.movieId!!)
        assertNotNull(movieEntity)
        assertEquals(movie.data?.movieId,movieEntity.movieId)
        assertEquals(movie.data?.title,movieEntity.title)
        assertEquals(movie.data?.year,movieEntity.year)

        viewModel.getMovie().observeForever(movieObserverResource)
        verify(movieObserverResource).onChanged(movie)
    }

    @Test
    fun getTvShow() {
        viewModel.setTvShow(tvShow.data?.tvShowId!!)

        val tv = MutableLiveData<Resource<TvShowEntity>>()
        tv.value = tvShow

        `when`(movieRepository.getTvShow(tvShow.data?.tvShowId!!)).thenReturn(tv)
        val tvShowEntity = viewModel.getTvShow().value?.data as TvShowEntity
        verify(movieRepository).getTvShow(tvShow.data?.tvShowId!!)
        assertNotNull(tvShowEntity)
        assertEquals(tvShow.data?.tvShowId,tvShowEntity.tvShowId)
        assertEquals(tvShow.data?.title,tvShowEntity.title)
        assertEquals(tvShow.data?.year,tvShowEntity.year)

        viewModel.getTvShow().observeForever(tvShowObserverResource)
        verify(tvShowObserverResource).onChanged(tvShow)
    }

    @Test
    fun getFavoriteMovie(){
        viewModel.setMovie(movie.data?.movieId!!)
        val mv = MutableLiveData<MovieEntity>()
        mv.value = movie.data

        local.insertFavoriteMovie(movie.data!!)
        `when`(movieRepository.getFavoriteMovie(movie.data?.movieId!!)).thenReturn(mv)

        val movieEntity = viewModel.getFavoriteMovie().value as MovieEntity
        verify(movieRepository).getFavoriteMovie(movie.data?.movieId!!)
        assertNotNull(movieEntity)
        assertEquals(movie.data?.movieId,movieEntity.movieId)

        viewModel.getFavoriteMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(movieEntity)
    }

    @Test
    fun getFavoriteTvShow(){
        viewModel.setTvShow(tvShow.data?.tvShowId!!)
        val tv = MutableLiveData<TvShowEntity>()
        tv.value = tvShow.data

        local.insertFavoriteTvShow(tvShow.data!!)
        `when`(movieRepository.getFavoriteTvShow(tvShow.data?.tvShowId!!)).thenReturn(tv)

        val tvShowEntity = viewModel.getFavoriteTvShow().value as TvShowEntity
        verify(movieRepository).getFavoriteTvShow(tvShow.data?.tvShowId!!)
        assertNotNull(tvShowEntity)
        assertEquals(tvShow.data?.tvShowId,tvShowEntity.tvShowId)

        viewModel.getFavoriteTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(tvShowEntity)
    }

    @Test
    fun addDeleteFavoriteMovie(){
        val mv = MutableLiveData<MovieEntity>()
        mv.value = movie.data

        local.insertFavoriteMovie(movie.data!!)
        `when`(movieRepository.getFavoriteMovie(movie.data?.movieId!!)).thenReturn(mv)
        viewModel.setMovie(movie.data?.movieId!!)
        val movieEntity = viewModel.getFavoriteMovie().value
        assertEquals(movieEntity?.movieId,mv.value?.movieId)

        local.deleteFavoriteMovie(mv.value!!)
        viewModel.setMovie("")
        assertNull(viewModel.getFavoriteMovie())
    }

    @Test
    fun addDeleteFavoriteTvShow(){
        val tv = MutableLiveData<TvShowEntity>()
        tv.value = tvShow.data

        local.insertFavoriteTvShow(tvShow.data!!)
        `when`(movieRepository.getFavoriteTvShow(tvShow.data?.tvShowId!!)).thenReturn(tv)
        viewModel.setTvShow(tvShow.data?.tvShowId!!)
        val tvShowEntity = viewModel.getFavoriteTvShow().value
        assertEquals(tvShowEntity?.tvShowId,tv.value?.tvShowId)

        local.deleteFavoriteTvShow(tv.value!!)
        viewModel.setTvShow("")
        assertNull(viewModel.getFavoriteTvShow())
    }
}