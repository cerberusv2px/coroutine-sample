package com.rosia.coroutinesample.data.local.comments

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentLocalModel(
	@PrimaryKey
	@ColumnInfo(name = "id") var commentId: Int,
	@ColumnInfo(name = "postId") var postId: Int,
	@ColumnInfo(name = "name") var name: String,
	@ColumnInfo(name = "email") var email: String,
	@ColumnInfo(name = "body") var body: String
)