package com.example.moviecatalogue.ui.tvshow

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
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.databinding.FragmentTvShowsBinding
import com.example.moviecatalogue.ui.main.MainActivity
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class TvShowsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var viewModel: TvShowViewModel
    private lateinit var adapter: TvShowAdapter
    private lateinit var edtSearh: EditText
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(requireActivity(),factory).get(TvShowViewModel::class.java)
        adapter = TvShowAdapter(activity as Context)

        binding.rvTvShow.layoutManager = LinearLayoutManager(context)
        binding.rvTvShow.setHasFixedSize(true)
        binding.rvTvShow.adapter = adapter

        binding.progressbar.visibility = View.VISIBLE

        if(activity != null){
            edtSearh = activity!!.findViewById(R.id.edtSearch)
            viewPager = activity!!.findViewById(R.id.view_pager)
        }

        edtSearh.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(viewPager.currentItem == 0){
                    binding.progressbar.visibility = View.VISIBLE

                    if(activity is MainActivity){
                        viewModel.searchTvShow(text.toString()).observe( requireActivity() , { tvShow ->
                            binding.progressbar.visibility = View.GONE
                            if(tvShow.isNotEmpty() || tvShow.isNullOrEmpty()){
                                adapter.submitList(null)
                                if(tvShow.size == 0){
                                    binding.emptyAnimation.visibility = View.VISIBLE
                                }else{
                                    binding.emptyAnimation.visibility = View.GONE
                                    adapter.submitList(tvShow)
                                }
                            }
                        })
                    }else{
                        viewModel.searchFavoritesTvShow(text.toString()).observe( requireActivity() , { tvShow ->
                            binding.progressbar.visibility = View.GONE
                            if(tvShow.isNotEmpty() || tvShow.isNullOrEmpty()){
                                adapter.submitList(null)
                                if(tvShow.size == 0){
                                    binding.emptyAnimation.visibility = View.VISIBLE
                                }else{
                                    binding.emptyAnimation.visibility = View.GONE
                                    adapter.submitList(tvShow)
                                }
                            }
                        })
                    }

                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        if(activity is MainActivity ){
            viewModel.getTvShows().observe(this , { tvShows ->
                if(tvShows != null){
                    when(tvShows.status){
                        Status.LOADING -> binding.progressbar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressbar.visibility = View.GONE
                            adapter.submitList(tvShows.data)

                            if(tvShows.data?.size == 0){
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
            viewModel.getAllFavoriteTvShow().observe(this,{ tvShows ->
                binding.progressbar.visibility = View.GONE
                adapter.submitList(tvShows)

                if(tvShows.size == 0){
                    binding.emptyAnimation.visibility = View.VISIBLE
                }else{
                    binding.emptyAnimation.visibility = View.GONE
                }
            })
        }



    }


}