package com.rosia.coroutinesample.data.remote

import com.rosia.coroutinesample.domain.PostRepository
import javax.inject.Inject

class PostRemoteImpl @Inject constructor(
	private val apiService: ApiService
) : PostRepository.Remote {

	override suspend fun fetchPosts(): List<PostRemoteModel> {
		return apiService.getPosts()
	}

	override suspend fun fetchComments(): List<CommentRemoteModel> {
		return apiService.getComments()
	}
}