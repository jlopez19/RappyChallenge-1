package com.jlopez.rappychallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jlopez.rappychallenge.repository.home.HomeRepositoryImp
import com.jlopez.rappychallenge.source.local.dao.MoviePopularDao
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import com.jlopez.rappychallenge.source.remote.Api
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val mockWebServer = MockWebServer()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    private val repository = HomeRepositoryImp(api, MockMoviePopular())

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Get popular list`() {
        runBlocking {
            val movies = repository.getPopularListLocal()
            assertEquals(2, movies.value?.size)
        }
    }


    @Test
    fun `Search movie`() {
        runBlocking {
            val search = repository.searchMovieLocal("test")
            assertEquals(1, search.value?.size)
        }
    }

}


class MockMoviePopular : MoviePopularDao {

    val movies = MutableLiveData(listOf(
        MoviePopularEntity(1, "description", "2021-02-12", "false", "","","","","movie 1 ","", 8.1, "1", "video", 0),
        MoviePopularEntity(2, "description 2", "2021-02-12", "false", "","","","","movie 2 ","", 8.1, "1", "video", 0),
        MoviePopularEntity(2, "test", "2021-02-12", "false", "","","","","testing","", 8.1, "1", "video", 0),
    ))

    override fun insertPopularList(results: List<MoviePopularEntity>?) {
        TODO("Not yet implemented")
    }

    override fun createMoviePopular(movie: MoviePopularEntity) {
        val newMovie = MoviePopularEntity(3, "description 3", "2022-02-12", "false", "","","","","movie 3","", 8.1, "1", "video", 0)
        movies.value = movies.value!!.toMutableList().apply { this!!.add(movie) }
    }

    override fun updateMoviePopular(movie: MoviePopularEntity) {
        movies.value = movies.value!!.toMutableList().apply { this!!.add(movie) }
    }

    override fun getMovieById(id: Int): MoviePopularEntity {
        TODO("Not yet implemented")
    }

    override fun getMoviesPopular(): LiveData<MutableList<MoviePopularEntity>> {
        return movies as LiveData<MutableList<MoviePopularEntity>>
    }

    override fun getMoviesTop(): LiveData<MutableList<MoviePopularEntity>> {
        TODO("Not yet implemented")
    }

    override fun searchMovie(query: String): LiveData<MutableList<MoviePopularEntity>> {
        val query = query.replace("%","")
        val movies =  movies.value?.filter {
            it.title.toString().contains(query)
        }

        return MutableLiveData(movies) as LiveData<MutableList<MoviePopularEntity>>

    }

}