package com.jlopez.rappychallenge.utils

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jlopez.rappychallenge.di.module.repository.RepositoryModule
import com.jlopez.rappychallenge.repository.home.HomeRepository
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule() : Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    @Provides
    @Singleton
    fun providerNewsRepository(): HomeRepository =
        object : HomeRepository {

            val movies = MutableLiveData(listOf(
                MoviePopularEntity(5, "description 5", "2021-02-12", "false", "","","","","movie 1 ","", 8.1, "1", "video", 0),
                MoviePopularEntity(6, "description 6", "2021-02-12", "false", "","","","","movie 2 ","", 8.1, "1", "video", 0)
            ))

            override suspend fun getPopularList() {

            }

            override suspend fun getPopularListLocal(): LiveData<MutableList<MoviePopularEntity>> {
                TODO("Not yet implemented")
            }

            override suspend fun getTopList() {
                TODO("Not yet implemented")
            }

            override suspend fun getTopListLocal(): LiveData<MutableList<MoviePopularEntity>> {
                return movies as LiveData<MutableList<MoviePopularEntity>>
            }

            override suspend fun searchMovie(newText: String) {
                TODO("Not yet implemented")
            }

            override fun searchMovieLocal(query: String): LiveData<MutableList<MoviePopularEntity>> {
                TODO("Not yet implemented")
            }
        }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FakeRepositoryModule> {
        override fun createFromParcel(parcel: Parcel): FakeRepositoryModule {
            return FakeRepositoryModule(parcel)
        }

        override fun newArray(size: Int): Array<FakeRepositoryModule?> {
            return arrayOfNulls(size)
        }
    }
}