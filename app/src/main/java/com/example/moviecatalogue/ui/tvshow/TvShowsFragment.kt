package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvShowsBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import com.example.moviecatalogue.vo.Status

class TvShowsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding
    private lateinit var viewModel: TvShowViewModel

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
        viewModel = ViewModelProvider(this,factory).get(TvShowViewModel::class.java)

        val adapter = TvShowAdapter()

        binding.rvTvShow.layoutManager = LinearLayoutManager(context)
        binding.rvTvShow.setHasFixedSize(true)
        binding.rvTvShow.adapter = adapter

        binding.progressbar.visibility = View.VISIBLE

        viewModel.getTvShows().observe(this , { tvShows ->
            if(tvShows != null){
                when(tvShows.status){
                    Status.LOADING -> binding.progressbar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        binding.progressbar.visibility = View.GONE
                        adapter.setData(tvShows.data)
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