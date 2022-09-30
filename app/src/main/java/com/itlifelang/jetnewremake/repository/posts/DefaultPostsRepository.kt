package com.itlifelang.jetnewremake.repository.posts

import com.itlifelang.jetnewremake.data.posts
import com.itlifelang.jetnewremake.di.DefaultDispatcher
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.model.PostsFeed
import com.itlifelang.jetnewremake.repository.Result
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DefaultPostsRepository @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : PostsRepository {
    override fun getPost(postId: String?): Flow<Result<Post>> {
        return flow {
            emit(Result.Loading)
            delay(3000L)
            val post = posts.allPosts.find { it.id == postId }
            if (post != null) {
                emit(Result.Success(post))
            } else {
                emit(Result.Error(Exception("Unable to find post")))
            }
        }.flowOn(dispatcher)
    }

    override fun getPostsFeed(): Flow<Result<PostsFeed>> {
        return flow {
            emit(Result.Loading)
            delay(3000L)
            emit(Result.Success(posts))
        }.flowOn(dispatcher)
    }

    override fun observeFavorites(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleFavorite(postId: String) {
        TODO("Not yet implemented")
    }
}