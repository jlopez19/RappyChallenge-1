package com.jlopez.rappychallenge.ui.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.jlopez.rappychallenge.databinding.FragmentMovieDetailBinding
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import com.jlopez.rappychallenge.ui.movieDetail.adapter.VideoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    private val args: MovieDetailFragmentArgs by navArgs()

    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        setupDetails(args.movie)
        viewModel.movie = args.movie
        viewModel.onCreate()
        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        videoAdapter = VideoAdapter(object : VideoAdapter.ClickListener{

        }, requireContext(), lifecycle)
        binding.recyclerVideo.adapter = videoAdapter

        lifecycle
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.videos.observe(viewLifecycleOwner, Observer {
            videoAdapter.submitList(it)
        })

    }

    private fun setupDetails(movie:  MoviePopularEntity) {
        val url = "https://www.themoviedb.org/t/p/w220_and_h330_face/${movie.backdropPath}"
        Glide.with(requireContext())
            .load(url)
            .into(binding.imageViewPoster)
        binding.collapsingToolBarLayout.title = movie.title
        binding.textViewRate.text = movie.voteAverage.toString()
        binding.textviewDescription.text = movie.overview
        binding.textViewDate.text = movie.releaseDate

    }
}