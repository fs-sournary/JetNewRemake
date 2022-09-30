package com.itlifelang.jetnewremake.di

import com.itlifelang.jetnewremake.repository.interest.DefaultInterestRepository
import com.itlifelang.jetnewremake.repository.interest.InterestRepository
import com.itlifelang.jetnewremake.repository.posts.DefaultPostsRepository
import com.itlifelang.jetnewremake.repository.posts.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providePostsRepository(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): PostsRepository {
        return DefaultPostsRepository(defaultDispatcher)
    }

    @Provides
    @Singleton
    fun provideInterestRepository(
        @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
    ): InterestRepository {
        return DefaultInterestRepository(defaultDispatcher)
    }
}
