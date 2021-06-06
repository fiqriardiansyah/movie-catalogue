package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentMoviesBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MoviesAdapter()

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this,factory).get(MovieViewModel::class.java)

        binding.progressbar.visibility = View.VISIBLE

        with(binding){
            rvMovie.layoutManager = LinearLayoutManager(context)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        viewModel.getMovies().observe(this,{ movies ->
            if(movies != null){
                when(movies.status){
                    Status.LOADING -> binding.progressbar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressbar.visibility = View.GONE
                        adapter.setData(movies = movies.data)
                    }
                    Status.ERROR -> {
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }
}