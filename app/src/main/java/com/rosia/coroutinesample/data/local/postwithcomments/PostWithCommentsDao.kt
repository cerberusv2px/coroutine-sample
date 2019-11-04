package com.rosia.coroutinesample.data.local.postwithcomments

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PostWithCommentsDao {

	@Query("select * from posts")
	suspend fun getPostWithComments(): List<PostWithComments>
}