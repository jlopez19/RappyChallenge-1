package com.jlopez.rappychallenge.source.remote.dto

import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import com.google.gson.annotations.SerializedName

data class MoviePopularResDTO(

    @SerializedName( "id") var id: Int,
    @SerializedName("overview") var overview: String,
    @SerializedName("release_date") var releaseDate: String,
    @SerializedName("first_air_date") var firstAirDate: String,
    @SerializedName("adult") var adult: String,
    @SerializedName("backdrop_path") var backdropPath: String,
    @SerializedName("vote_count") var voteCount: String,
    @SerializedName("original_language") var originalLanguage: String,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("title") var title: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("video") var video: String,
    @SerializedName("vote_average") var voteAverage: Double,
    @SerializedName("popularity") var popularity: String,
    @SerializedName("media_type") var mediaType: String
){
    fun toMoviePopularEntity(): MoviePopularEntity {
        return MoviePopularEntity(
            id = id,
            overview = overview,
            releaseDate = releaseDate?:firstAirDate,
            adult = adult,
            backdropPath = backdropPath,
            voteCount = voteCount,
            originalLanguage = originalLanguage,
            posterPath = posterPath,
            title = title?:name,
            video = video,
            voteAverage = voteAverage,
            popularity = popularity,
            mediaType = mediaType
        )
    }
}
