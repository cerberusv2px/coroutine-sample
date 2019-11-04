package com.rosia.coroutinesample.data.local

import android.content.Context
import com.rosia.coroutinesample.data.local.comments.CommentDao
import com.rosia.coroutinesample.data.local.post.PostDao
import com.rosia.coroutinesample.data.local.postwithcomments.PostWithCommentsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseManager @Inject constructor(var context: Context) {

	private val instance = PostDatabase.getInstance(context)

	fun dropDatabase() {
		instance.clearAllTables()
	}

	fun getInstance() = instance

	fun getUserDao(): PostDao = instance.getPostDao()
	fun getCommentsDao(): CommentDao = instance.getCommentDao()
	fun getPostWithCommentDao(): PostWithCommentsDao = instance.getPostWithCommentDao()
}