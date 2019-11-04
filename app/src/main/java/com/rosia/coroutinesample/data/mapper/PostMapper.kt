package com.rosia.coroutinesample.data.mapper

import com.rosia.coroutinesample.data.local.post.PostLocalModel
import com.rosia.coroutinesample.data.remote.PostRemoteModel

object PostMapper {

	fun mapToLocal(post: PostRemoteModel): PostLocalModel {
		return PostLocalModel(
			id = post.id,
			userId = post.userId,
			title = post.title,
			body = post.body
		)
	}
}