package com.jlopez.rappychallenge.source.remote.dto

import com.jlopez.rappychallenge.source.local.entity.VideoEntity
import com.google.gson.annotations.SerializedName

data class VideoModelResDTO (
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("key") var key: String,
    @SerializedName("site") var site: String,
    @SerializedName("size") var size: String

) {
    fun toVideoEntity(): VideoEntity {
        return VideoEntity(
            id = id,
            name = id,
            key = key,
            site = site,
            size = size
        )
    }
}