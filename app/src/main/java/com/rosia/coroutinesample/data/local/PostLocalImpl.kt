package com.rosia.coroutinesample.data.local

import com.rosia.coroutinesample.domain.PostRepository
import javax.inject.Inject

class PostLocalImpl @Inject constructor(
	private val databaseManager: DatabaseManager
) : PostRepository.Local {

	override suspend fun savePosts(posts: List<PostLocalModel>) {
		databaseManager.getUserDao().insert(posts)
	}

	override suspend fun getAllPosts(): List<PostLocalModel> {
		return databaseManager.getUserDao().getAll()
	}
}