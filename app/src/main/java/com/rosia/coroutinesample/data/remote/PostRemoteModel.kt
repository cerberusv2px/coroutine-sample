package com.rosia.coroutinesample.data.remote

import com.google.gson.annotations.SerializedName

data class PostRemoteModel(
	@SerializedName("id") var id: Int,
	@SerializedName("userId") var userId: Int,
	@SerializedName("title") var title: String,
	@SerializedName("body") var body: String
)