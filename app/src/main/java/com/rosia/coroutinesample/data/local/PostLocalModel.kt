package com.rosia.coroutinesample.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostLocalModel(
	@PrimaryKey
	@ColumnInfo(name = "id") var id: Int,
	@ColumnInfo(name = "user_id") var userId: Int,
	@ColumnInfo(name = "title") var title: String,
	@ColumnInfo(name = "body") var body: String
)