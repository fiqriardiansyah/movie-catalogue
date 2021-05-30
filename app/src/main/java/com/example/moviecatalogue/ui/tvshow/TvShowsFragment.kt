package com.example.moviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvShowsBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class TvShowsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding

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
        val viewModel = ViewModelProvider(this,factory).get(TvShowViewModel::class.java)

        val adapter = TvShowAdapter()

        binding.rvTvShow.layoutManager = LinearLayoutManager(context)
        binding.rvTvShow.setHasFixedSize(true)
        binding.rvTvShow.adapter = adapter

        binding.progressbar.visibility = View.VISIBLE

        viewModel.getTvShows().observe(this , { tvShows ->
            binding.progressbar.visibility = View.INVISIBLE
            adapter.setData(tvShows)
            adapter.notifyDataSetChanged()
        })

    }
}