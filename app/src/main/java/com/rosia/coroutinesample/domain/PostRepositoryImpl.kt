package com.rosia.coroutinesample.domain

import com.rosia.coroutinesample.data.local.comments.CommentLocalModel
import com.rosia.coroutinesample.data.local.post.PostLocalModel
import com.rosia.coroutinesample.data.local.postwithcomments.PostWithComments
import com.rosia.coroutinesample.data.mapper.CommentMapper
import com.rosia.coroutinesample.data.mapper.PostMapper
import com.rosia.coroutinesample.data.remote.CommentRemoteModel
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
			val posts = postRemoteRepository.fetchPosts()
			val comments = postRemoteRepository.fetchComments()
			val localPosts = posts.map {
				PostMapper.mapToLocal(it)
			}

			val localComments = comments.map {
				CommentMapper.mapToLocal(it)
			}

			savePosts(localPosts)
			saveComments(localComments)
			posts

		}
	}

	override suspend fun fetchComments(): List<CommentRemoteModel> {
		return emptyList()
	}

	override suspend fun saveComments(comments: List<CommentLocalModel>) {
		postLocalRepository.saveComments(comments)
	}

	override suspend fun getPostWithComments(): List<PostWithComments> {
		return withContext(Dispatchers.IO) {
			postLocalRepository.getPostWithComments()
		}
	}
}