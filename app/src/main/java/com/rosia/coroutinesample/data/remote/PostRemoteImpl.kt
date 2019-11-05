package com.rosia.coroutinesample.data.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import com.rosia.coroutinesample.CharactersQuery
import com.rosia.coroutinesample.data.remote.apollo.ResultModel
import com.rosia.coroutinesample.data.remote.apollo.RootModel
import com.rosia.coroutinesample.domain.PostRepository
import javax.inject.Inject

class PostRemoteImpl @Inject constructor(
	private val apiService: ApiService,
	private val apolloClient: ApolloClient
) : PostRepository.Remote {

	override suspend fun fetchPosts(): List<PostRemoteModel> {
		return apiService.getPosts()
	}

	override suspend fun fetchComments(): List<CommentRemoteModel> {
		return apiService.getComments()
	}

	override suspend fun fetchRickAndMortyData(): List<ResultModel> {
		val characterQuery = CharactersQuery.builder().build()
		val deferred = apolloClient.query(characterQuery).toDeferred()
		val response = deferred.await()
		val rootModelList =  NotNullApolloMapper().apply<CharactersQuery.Data, RootModel>(response)
		return rootModelList.characters.results
	}
}