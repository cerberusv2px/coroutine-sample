package com.rosia.coroutinesample.data.mapper

import com.rosia.coroutinesample.data.local.comments.CommentLocalModel
import com.rosia.coroutinesample.data.remote.CommentRemoteModel

object CommentMapper {

	fun mapToLocal(remoteModel: CommentRemoteModel): CommentLocalModel {
		return CommentLocalModel(
			commentId = remoteModel.id,
			postId = remoteModel.postId,
			name = remoteModel.name,
			body = remoteModel.body,
			email = remoteModel.email
		)
	}
}