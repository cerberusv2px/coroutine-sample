package com.rosia.coroutinesample.domain

import com.rosia.coroutinesample.data.local.PostLocalModel
import com.rosia.coroutinesample.data.mapper.PostMapper
import com.rosia.coroutinesample.data.remote.PostRemoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
	private val postLocalRepository: PostRepository.Local,
	private val postRemoteRepository: PostRepository.Remote
) : PostRepository.Remote, PostRepository.Local {

	override suspend fun savePosts(posts: List<PostLocalModel>) {
		postLocalRepository.savePosts(posts)
	}

	override suspend fun getAllPosts(): List<PostLocalModel> {
		return withContext(Dispatchers.IO) {
			postLocalRepository.getAllPosts()
		}
	}

	override suspend fun fetchPosts(): List<PostRemoteModel> {
		return withContext(Dispatchers.IO) {
			try {
				val posts = postRemoteRepository.fetchPosts()
				val localPosts = posts.map {
					PostMapper.mapToLocal(it)
				}
				savePosts(localPosts)
				posts
			} catch (error: Exception) {
				throw error
			}

		}
	}
}