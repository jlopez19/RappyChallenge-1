package com.jlopez.rappychallenge.source.remote

import com.jlopez.rappychallenge.source.remote.dto.PopularResDTO
import com.jlopez.rappychallenge.source.remote.dto.VideoResDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("trending/all/day?api_key=b651d167d89bc3571669176f6101648e")
    suspend fun getPopularList() : Response<PopularResDTO>

    @GET("discover/movie?api_key=b651d167d89bc3571669176f6101648e&language=es-COL&vote_average.gte=5")
    suspend fun getTopList() : Response<PopularResDTO>

    @GET("search/{type}?api_key=b651d167d89bc3571669176f6101648e&language=es-COL&page=1&include_adult=false")
    suspend fun searchMovie(@Path("type") type: String, @Query("query") query: String) : Response<PopularResDTO>

    @GET("movie/{id}/videos?api_key=b651d167d89bc3571669176f6101648e&language=es-COL")
    suspend fun getVideos(@Path("id") id: Int) : Response<VideoResDTO>


}
