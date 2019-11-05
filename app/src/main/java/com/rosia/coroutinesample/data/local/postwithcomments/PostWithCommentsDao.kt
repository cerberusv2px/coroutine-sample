package com.rosia.coroutinesample.data.local.postwithcomments

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PostWithCommentsDao {

	@Query("select * from posts where id = 1")
	fun getPostWithComments(): LiveData<List<PostWithComments>>
}