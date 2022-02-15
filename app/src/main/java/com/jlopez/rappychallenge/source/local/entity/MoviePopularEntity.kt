package com.jlopez.rappychallenge.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "moviepopular")
data class MoviePopularEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "release_date") var releaseDate: String?,
    @ColumnInfo(name = "adult") var adult: String?,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String?,
    @ColumnInfo(name = "vote_count") var voteCount: String?,
    @ColumnInfo(name = "original_language") var originalLanguage: String?,
    @ColumnInfo(name = "poster_path") var posterPath: String?,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "video") var video: String?,
    @ColumnInfo(name = "vote_average") var voteAverage: Double?,
    @ColumnInfo(name = "popularity") var popularity: String?,
    @ColumnInfo(name = "media_type") var mediaType: String?,
    @ColumnInfo(name = "type") var type: Int? = 0


): Serializable{

}
