package com.itlifelang.jetnewremake.repository.posts

import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.model.PostsFeed
import com.itlifelang.jetnewremake.repository.Result
import kotlinx.coroutines.flow.Flow

/**
 * Interface to the Posts data layer.
 */
interface PostsRepository {

    /**
     * Get a specific JetNews post.
     */
    fun getPost(postId: String?): Flow<Result<Post>>

    /**
     * Get JetNews posts.
     */
    fun getPostsFeed(): Flow<Result<PostsFeed>>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<String>>

    /**
     * Toggle a postId to be a favorite or not.
     */
    suspend fun toggleFavorite(postId: String)
}
