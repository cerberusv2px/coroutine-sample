package com.rosia.coroutinesample.data.remote.apollo

import com.google.gson.annotations.SerializedName

data class RootModel(
	@SerializedName("characters") var characters: CharacterModel
)

data class CharacterModel(
	@SerializedName("results") var results: List<ResultModel>
)

data class ResultModel(
	@SerializedName("id") var id: Int,
	@SerializedName("name") var name: String,
	@SerializedName("species") var species: String,
	@SerializedName("status") var status: String,
	@SerializedName("type") var type: String
)