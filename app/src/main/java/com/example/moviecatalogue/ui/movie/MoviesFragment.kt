package com.example.moviecatalogue.ui.movie

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.databinding.FragmentMoviesBinding
import com.example.moviecatalogue.ui.main.MainActivity
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MoviesAdapter
    private lateinit var edtSearh: EditText
    private lateinit var viewPager: ViewPager

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

        if(activity != null){
            edtSearh = activity!!.findViewById(R.id.edtSearch)
            viewPager = activity!!.findViewById(R.id.view_pager)
        }

        edtSearh.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(viewPager.currentItem == 1){
                    binding.progressbar.visibility = View.VISIBLE

                    if(activity is MainActivity){
                        viewModel.searchMovie(text.toString()).observe( requireActivity() , { movies ->
                            binding.progressbar.visibility = View.GONE
                            if(movies.isNotEmpty() || movies.isNullOrEmpty()){
                                adapter.submitList(null)
                                if(movies.size == 0){
                                    binding.emptyAnimation.visibility = View.VISIBLE
                                }else{
                                    binding.emptyAnimation.visibility = View.GONE
                                    adapter.submitList(movies)
                                }
                            }
                        })
                    }else{
                        viewModel.searchFavoritesMovie(text.toString()).observe( requireActivity() , { movies ->
                            binding.progressbar.visibility = View.GONE
                            if(movies.isNotEmpty() || movies.isNullOrEmpty()){
                                adapter.submitList(null)
                                if(movies.size == 0){
                                    binding.emptyAnimation.visibility = View.VISIBLE
                                }else{
                                    binding.emptyAnimation.visibility = View.GONE
                                    adapter.submitList(movies)
                                }
                            }
                        })
                    }

                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

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