package com.itlifelang.jetnewremake.repository.interest

import com.itlifelang.jetnewremake.di.DefaultDispatcher
import com.itlifelang.jetnewremake.model.TopicSection
import com.itlifelang.jetnewremake.model.TopicSelection
import com.itlifelang.jetnewremake.repository.Result
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DefaultInterestRepository @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : InterestRepository {
    override fun getTopicSections(): Flow<Result<List<TopicSection>>> {
        return flow {
            emit(Result.Loading)
            delay(3000L)
            val topicSections = listOf(
                TopicSection(
                    title = "Android",
                    interests = listOf("Jetpack Compose", "Kotlin", "Jetpack")
                ),
                TopicSection(
                    title = "Programming",
                    interests = listOf(
                        "Kotlin",
                        "Declarative UIs",
                        "Java",
                        "Unidirectional Data Flow",
                        "C++"
                    )
                ),
                TopicSection(
                    title = "Technology",
                    interests = listOf("Pixel", "Google")
                )
            )
            emit(Result.Success(topicSections))
        }.flowOn(dispatcher)
    }

    override fun getPeople(): Flow<Result<List<String>>> {
        return flow {
            emit(Result.Loading)
            delay(3000L)
            val people = listOf(
                "Kobalt Toral",
                "K'Kola Uvarek",
                "Kris Vriloc",
                "Grala Valdyr",
                "Kruel Valaxar",
                "L'Elij Venonn",
                "Kraag Solazarn",
                "Tava Targesh",
                "Kemarrin Muuda"
            )
            emit(Result.Success(people))
        }.flowOn(dispatcher)
    }

    override fun getPublications(): Flow<Result<List<String>>> {
        return flow {
            emit(Result.Loading)
            delay(3000L)
            val publications = listOf(
                "Kotlin Vibe",
                "Compose Mix",
                "Compose Breakdown",
                "Android Pursue",
                "Kotlin Watchman",
                "Jetpack Ark",
                "Composeshack",
                "Jetpack Point",
                "Compose Tribune"
            )
            emit(Result.Success(publications))
        }
    }

    override suspend fun toggleTopicSelection(topic: TopicSelection) {
        TODO("Not yet implemented")
    }

    override suspend fun togglePersonSelected(person: String) {
        TODO("Not yet implemented")
    }

    override suspend fun togglePublicationSelected(publication: String) {
        TODO("Not yet implemented")
    }

    override fun observeTopicsSelected(): Flow<Set<TopicSelection>> {
        TODO("Not yet implemented")
    }

    override fun observePeopleSelected(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override fun observePublicationSelected(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }
}