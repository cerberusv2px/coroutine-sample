package com.rosia.coroutinesample.data.local.postwithcomments

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PostWithCommentsDao {

	@Query("select * from posts")
	fun getPostWithComments(): List<PostWithComments>
}