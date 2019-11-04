package com.rosia.coroutinesample.data.local.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(data: List<PostLocalModel>)

	@Query("SELECT * FROM posts")
	fun getAll(): List<PostLocalModel>
}