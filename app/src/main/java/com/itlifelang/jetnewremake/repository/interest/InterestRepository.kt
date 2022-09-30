package com.itlifelang.jetnewremake.repository.interest

import com.itlifelang.jetnewremake.model.TopicSection
import com.itlifelang.jetnewremake.model.TopicSelection
import com.itlifelang.jetnewremake.repository.Result
import kotlinx.coroutines.flow.Flow

interface InterestRepository {

    /**
     * Get relevant topics to the user.
     */
    fun getTopicSections(): Flow<Result<List<TopicSection>>>

    /**
     * Get list of people.
     */
    fun getPeople(): Flow<Result<List<String>>>

    /**
     * Get list of publications.
     */
    fun getPublications(): Flow<Result<List<String>>>

    /**
     * Toggle between selected and unselected
     */
    suspend fun toggleTopicSelection(topic: TopicSelection)

    /**
     * Toggle between selected and unselected
     */
    suspend fun togglePersonSelected(person: String)

    /**
     * Toggle between selected and unselected
     */
    suspend fun togglePublicationSelected(publication: String)

    /**
     * Currently selected topics
     */
    fun observeTopicsSelected(): Flow<Set<TopicSelection>>

    /**
     * Currently selected people
     */
    fun observePeopleSelected(): Flow<Set<String>>

    /**
     * Currently selected publications
     */
    fun observePublicationSelected(): Flow<Set<String>>
}
