package com.jlopez.rappychallenge.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "key") var key: String,
    @ColumnInfo(name = "site") var site: String,
    @ColumnInfo(name = "size") var size: String,
    @ColumnInfo(name = "movie_id") var movieId: Int? = 0,

): Serializable{

}
