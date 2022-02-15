package com.jlopez.rappychallenge.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlopez.rappychallenge.repository.home.HomeRepository
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    private lateinit var _moviesPopular : LiveData<MutableList<MoviePopularEntity>>
    val moviesPopular : LiveData<MutableList<MoviePopularEntity>>
        get() =_moviesPopular


    private lateinit var _moviesTop : LiveData<MutableList<MoviePopularEntity>>
    val moviesTop : LiveData<MutableList<MoviePopularEntity>>
        get() =_moviesTop


    private lateinit var _moviesSearch : LiveData<MutableList<MoviePopularEntity>>
    val moviesSearch : LiveData<MutableList<MoviePopularEntity>>
        get() =_moviesSearch

    var textSearch = ""

    fun onCreate() {
        getPopularList()
        getTopList()
        getPopularListLocal()
        getTopListLocal()

    }

    private fun getTopList() {
        viewModelScope.launch {
            try{
                repository.getTopList()

            }catch (ex: Exception){
                Log.e("Error: ", ex.toString())
            }
        }
    }

    private fun getPopularList() {
        viewModelScope.launch {
            try{
                repository.getPopularList()

            }catch (ex: Exception){
                Log.e("Error: ", ex.toString())
            }
        }
    }


    fun getPopularListLocal(){
        viewModelScope.launch {
            _moviesPopular = repository.getPopularListLocal()
        }
    }

    fun getTopListLocal(){
        viewModelScope.launch {
            _moviesTop = repository.getTopListLocal()
        }
    }

    fun searchMovie(newText: String) {

        viewModelScope.launch {
            try{
                repository.searchMovie(newText)
            }catch (ex: Exception){
                Log.e("Error: ", ex.toString())
            }
        }

    }

    fun searchMovieLocal(){
        //.launch {
            try{
                _moviesSearch = repository.searchMovieLocal(textSearch)

            }catch (ex: Exception){
                Log.e("Error: ", ex.toString())
            }
        //}
    }

    fun resetText(){
        textSearch = ""
    }


}