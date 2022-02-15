package com.jlopez.rappychallenge.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jlopez.rappychallenge.R
import com.jlopez.rappychallenge.databinding.FragmentHomeBinding
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import com.jlopez.rappychallenge.ui.home.adapter.AdapterPopular
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapterPopular: AdapterPopular

    private lateinit var adapterTop: AdapterPopular

    private lateinit var adapterSearch: AdapterPopular


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.onCreate()

        setAdapter()

        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.moviesPopular.observe(viewLifecycleOwner, Observer {
            adapterPopular.submitList(it)
        })

        viewModel.moviesTop.observe(viewLifecycleOwner, Observer {
            adapterTop.submitList(it)
        })


    }

    private fun setAdapter() {
        adapterPopular = AdapterPopular(object : AdapterPopular.MoviePopularClickListener{
            override fun onClick(item: MoviePopularEntity) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(item)
                )
            }

        }, requireContext())
        binding.recyclerPopular.adapter = adapterPopular


        adapterTop = AdapterPopular(object : AdapterPopular.MoviePopularClickListener{
            override fun onClick(item: MoviePopularEntity) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(item)
                )
            }

        }, requireContext())
        binding.recyclerTop.adapter = adapterTop

        adapterSearch = AdapterPopular(object : AdapterPopular.MoviePopularClickListener{
            override fun onClick(item: MoviePopularEntity) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(item)
                )
            }

        }, requireContext())
        binding.recyclerSearch.adapter = adapterSearch

    }


    private fun searchMovies(newText: String) {
        viewModel.searchMovie(newText)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu_home, menu)

        val searchItem = menu.findItem(R.id.action_search).actionView as SearchView

        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    if (query.length >= 2 ) {
                        viewModel.searchMovieLocal()
                        searchMovies(query)

                        viewModel.moviesSearch.observe(viewLifecycleOwner, Observer {
                            if (!it.isNullOrEmpty()){
                                adapterSearch.submitList(it)
                            }
                        })
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null) viewModel.textSearch = newText
                return true
            }

        })

        searchItem.setOnCloseListener(object : SearchView.OnCloseListener{
            override fun onClose(): Boolean {
                viewModel.resetText()
                return false
            }

        })

        searchItem.setOnQueryTextFocusChangeListener(object : View.OnFocusChangeListener{
            override fun onFocusChange(p0: View?, focus: Boolean) {
                binding.viewSwitcher.showNext()
            }

        })

    }


}