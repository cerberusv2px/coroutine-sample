package com.rosia.coroutinesample.data.local.postwithcomments

import androidx.room.Embedded
import androidx.room.Relation
import com.rosia.coroutinesample.data.local.comments.CommentLocalModel
import com.rosia.coroutinesample.data.local.post.PostLocalModel

data class PostWithComments(
	@Embedded var post: PostLocalModel,
	@Relation(parentColumn = "id", entityColumn = "postId", entity = CommentLocalModel::class) var comments: List<CommentLocalModel>
)