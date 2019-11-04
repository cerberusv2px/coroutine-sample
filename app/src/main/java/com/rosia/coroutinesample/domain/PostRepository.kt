package com.rosia.coroutinesample.domain

import androidx.lifecycle.LiveData
import com.rosia.coroutinesample.data.local.comments.CommentLocalModel
import com.rosia.coroutinesample.data.local.post.PostLocalModel
import com.rosia.coroutinesample.data.local.postwithcomments.PostWithComments
import com.rosia.coroutinesample.data.remote.CommentRemoteModel
import com.rosia.coroutinesample.data.remote.PostRemoteModel

interface PostRepository {

	interface Local {
		suspend fun savePosts(posts: List<PostLocalModel>)
		suspend fun getAllPosts(): List<PostLocalModel>

		suspend fun saveComments(comments: List<CommentLocalModel>)
		suspend fun getPostWithComments(): List<PostWithComments>
	}

	interface Remote {
		suspend fun fetchPosts(): List<PostRemoteModel>
		suspend fun fetchComments(): List<CommentRemoteModel>
	}
}