package com.rosia.coroutinesample.data.remote

import retrofit2.http.GET

interface ApiService {

	@GET("posts")
	suspend fun getPosts(): List<PostRemoteModel>

	@GET("comments")
	suspend fun getComments(): List<CommentRemoteModel>
}