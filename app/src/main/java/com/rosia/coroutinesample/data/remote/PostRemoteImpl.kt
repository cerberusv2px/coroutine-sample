package com.rosia.coroutinesample.data.remote

import com.rosia.coroutinesample.domain.PostRepository
import javax.inject.Inject

class PostRemoteImpl @Inject constructor(
	private val apiService: ApiService
) : PostRepository.Remote {

	override suspend fun fetchPosts(): List<PostRemoteModel> {
		val temp = apiService.getPosts()
		return temp.filter { it.id < 20 }
	}

	override suspend fun fetchComments(): List<CommentRemoteModel> {
		return apiService.getComments()
	}
}