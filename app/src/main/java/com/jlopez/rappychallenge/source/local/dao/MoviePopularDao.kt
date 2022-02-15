package com.jlopez.rappychallenge.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity

@Dao
interface MoviePopularDao {

    @Insert
    fun insertPopularList(results: List<MoviePopularEntity>?)

    @Insert
    fun createMoviePopular(movie: MoviePopularEntity)

    @Update
    fun updateMoviePopular(movie: MoviePopularEntity)

    @Query(
        "SELECT * FROM moviepopular WHERE id = :id"
    )
    fun getMovieById(id: Int) : MoviePopularEntity

    @Query(
        "SELECT * FROM moviepopular WHERE type = 0"
    )
    fun getMoviesPopular(): LiveData<MutableList<MoviePopularEntity>>


    @Query(
        "SELECT * FROM moviepopular WHERE type = 1 ORDER BY vote_average desc"
    )
    fun getMoviesTop(): LiveData<MutableList<MoviePopularEntity>>

    @Query(
        "SELECT * FROM moviepopular WHERE title LIKE :query AND poster_path IS NOT NULL AND backdrop_path IS NOT NULL  ORDER BY title ASC limit 20"
    )
    fun searchMovie(query: String): LiveData<MutableList<MoviePopularEntity>>



}