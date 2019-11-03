package com.rosia.coroutinesample.domain

import androidx.lifecycle.LiveData
import com.rosia.coroutinesample.data.local.PostLocalModel
import com.rosia.coroutinesample.data.remote.PostRemoteModel

interface PostRepository {

	interface Local {
		suspend fun savePosts(posts: List<PostLocalModel>)
		suspend fun getAllPosts(): List<PostLocalModel>
	}

	interface Remote {
		suspend fun fetchPosts(): List<PostRemoteModel>
	}
}