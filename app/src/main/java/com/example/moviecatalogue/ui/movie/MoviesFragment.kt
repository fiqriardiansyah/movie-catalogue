package com.example.moviecatalogue.ui.movie

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.moviecatalogue.databinding.FragmentMoviesBinding
import com.example.moviecatalogue.ui.main.MainActivity
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this,factory).get(MovieViewModel::class.java)
        adapter = MoviesAdapter(activity as Context)

        binding.progressbar.visibility = View.VISIBLE

        with(binding){
            rvMovie.layoutManager = LinearLayoutManager(context)
            rvMovie.setHasFixedSize(true)
            rvMovie.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        if(activity is MainActivity){
            viewModel.getMovies().observe(this,{ movies ->
                if(movies != null){
                    when(movies.status){
                        Status.LOADING -> binding.progressbar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressbar.visibility = View.GONE
                            adapter.submitList(movies.data)
                            if(movies.data?.size == 0){
                                binding.emptyAnimation.visibility = View.VISIBLE
                            }else{
                                binding.emptyAnimation.visibility = View.GONE
                            }
                        }
                        Status.ERROR -> {
                            binding.progressbar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }else{
            viewModel.getAllFavoriteMovies().observe(this,{ movies ->
                if(movies != null){
                    binding.progressbar.visibility = View.GONE
                    adapter.submitList(movies)
                    if(movies.size == 0){
                        binding.emptyAnimation.visibility = View.VISIBLE
                    }else{
                        binding.emptyAnimation.visibility = View.GONE
                    }
                }
            })
        }



    }

}