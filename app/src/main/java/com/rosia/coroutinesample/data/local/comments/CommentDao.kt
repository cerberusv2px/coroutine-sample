package com.rosia.coroutinesample.data.local.comments

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CommentDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(data: List<CommentLocalModel>)

	@Query("select * from comments")
	fun getAll(): List<CommentLocalModel>
}