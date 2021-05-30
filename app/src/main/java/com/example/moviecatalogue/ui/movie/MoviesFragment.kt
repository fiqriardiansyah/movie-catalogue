package com.example.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentMoviesBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

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
        val viewModel = ViewModelProvider(this,factory).get(MovieViewModel::class.java)

        binding.progressbar.visibility = View.VISIBLE

        with(binding){
            rvMovie.layoutManager = LinearLayoutManager(context)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        viewModel.getMovies().observe(this,{ movies ->

            binding.progressbar.visibility = View.INVISIBLE
            adapter.setData(movies)
            adapter.notifyDataSetChanged()
        })

    }
}