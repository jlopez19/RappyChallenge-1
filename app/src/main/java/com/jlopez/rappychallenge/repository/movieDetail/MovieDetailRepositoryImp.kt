package com.jlopez.rappychallenge.repository.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import com.jlopez.rappychallenge.source.local.dao.VideoDao
import com.jlopez.rappychallenge.source.local.entity.VideoEntity
import com.jlopez.rappychallenge.source.remote.Api
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDetailRepositoryImp @Inject constructor(
    private val api: Api,
    private val videoDao: VideoDao
) : MovieDetailRepository {
    override suspend fun getVideos(movieId: Int) {

        val response = api.getVideos(movieId)

        Log.e("elian", response.body().toString())

        if (response.isSuccessful){
            GlobalScope.launch {
                response.body()?.results?.forEach {

                    //search movie popular
                    var video: VideoEntity = it.toVideoEntity()
                    video.movieId = movieId


                    val movieLocal = videoDao.getVideo(video.id)

                    if(movieLocal != null) videoDao.updateVideo(video)
                    else videoDao.createVideo(video)

                }

            }
        }

    }

    override suspend fun getVideosLocal(movieId: Int): LiveData<MutableList<VideoEntity>> {
        return videoDao.getVideos(movieId)
    }

}