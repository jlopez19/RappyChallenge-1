package com.jlopez.rappychallenge.repository.home

import android.util.Log
import androidx.lifecycle.LiveData
import com.jlopez.rappychallenge.source.local.dao.MoviePopularDao
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import com.jlopez.rappychallenge.source.remote.Api
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImp @Inject constructor(
    private val api: Api,
    private val moviePopularDao: MoviePopularDao
) : HomeRepository {

    companion object{
        object MOVIE_TYPE  {
            const val POPULAR = 0
            const val TOP = 1
            const val ALL = 2
        }
    }

    override suspend fun getPopularList() {

        val response =  api.getPopularList()

        if (response.isSuccessful){
            GlobalScope.launch {
                response.body()?.results?.forEach {

                    //search movie popular
                    var movie: MoviePopularEntity = it.toMoviePopularEntity()

                    movie.type = MOVIE_TYPE.POPULAR

                    val movieLocal = moviePopularDao.getMovieById(movie.id)

                    try{
                        if(movieLocal != null) moviePopularDao.updateMoviePopular(movie)
                        else moviePopularDao.createMoviePopular(movie)

                    }catch (ex: Exception){
                        Log.d("Error: ", ex.toString())
                    }
                }

            }
        }
    }

    override suspend fun getPopularListLocal(): LiveData<MutableList<MoviePopularEntity>> {
        return moviePopularDao.getMoviesPopular()
    }

    override suspend fun getTopList() {

        val response = api.getTopList()

        if (response.isSuccessful){
            GlobalScope.launch {
                response.body()?.results?.forEach {

                    //search movie popular
                    var movie: MoviePopularEntity = it.toMoviePopularEntity()

                    movie.type = MOVIE_TYPE.TOP

                    val movieLocal = moviePopularDao.getMovieById(movie.id)

                    if(movieLocal != null) moviePopularDao.updateMoviePopular(movie)
                    else moviePopularDao.createMoviePopular(movie)

                }

            }
        }

    }


    override suspend fun getTopListLocal(): LiveData<MutableList<MoviePopularEntity>> {
        return moviePopularDao.getMoviesTop()
    }

    override suspend fun searchMovie(newText: String) {
        var response = api.searchMovie("movie",newText)

        if (response.isSuccessful){
            GlobalScope.launch {
                response.body()?.results?.forEach {

                    //search movie popular
                    var movie: MoviePopularEntity = it.toMoviePopularEntity()

                    movie.type = MOVIE_TYPE.ALL

                    val movieLocal = moviePopularDao.getMovieById(movie.id)

                    if(movieLocal != null) moviePopularDao.updateMoviePopular(movie)
                    else moviePopularDao.createMoviePopular(movie)

                }

            }
        }

        response = api.searchMovie("tv",newText)

        try{
            if (response.isSuccessful){
                GlobalScope.launch {
                    response.body()?.results?.forEach {

                        //search movie popular
                        var movie: MoviePopularEntity = it.toMoviePopularEntity()

                        movie.type = MOVIE_TYPE.ALL

                        val movieLocal = moviePopularDao.getMovieById(movie.id)

                        if(movieLocal != null) moviePopularDao.updateMoviePopular(movie)
                        else moviePopularDao.createMoviePopular(movie)

                    }

                }
            }
        }catch (ex: Exception){
            Log.e("Error: ", ex.toString())
        }

    }

    override fun searchMovieLocal(query: String): LiveData<MutableList<MoviePopularEntity>> {

        return moviePopularDao.searchMovie("%$query%")

    }

}