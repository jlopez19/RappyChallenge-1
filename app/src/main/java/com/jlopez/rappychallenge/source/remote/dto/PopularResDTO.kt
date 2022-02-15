package com.jlopez.rappychallenge.source.remote.dto

import com.google.gson.annotations.SerializedName

data class PopularResDTO(
    @SerializedName("results") val  results : List<MoviePopularResDTO>
)
