package com.jlopez.rappychallenge.source.remote.dto

import com.google.gson.annotations.SerializedName

data class VideoResDTO(
    @SerializedName("results") val  results : List<VideoModelResDTO>
)
