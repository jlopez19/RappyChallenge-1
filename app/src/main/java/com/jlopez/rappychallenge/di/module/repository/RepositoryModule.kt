package com.jlopez.rappychallenge.di.module.repository

import com.jlopez.rappychallenge.repository.home.HomeRepository
import com.jlopez.rappychallenge.repository.home.HomeRepositoryImp
import com.jlopez.rappychallenge.repository.movieDetail.MovieDetailRepository
import com.jlopez.rappychallenge.repository.movieDetail.MovieDetailRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

     @Binds
     abstract fun bindHomeRepository(repository: HomeRepositoryImp ): HomeRepository

     @Binds
     abstract fun bindMovieDetailRepository(repository: MovieDetailRepositoryImp ): MovieDetailRepository


}