package com.rosia.coroutinesample.data.remote

import com.apollographql.apollo.api.Response
import com.google.gson.Gson

class NotNullApolloMapper {

	val gson = Gson()

	inline fun <reified T, reified R> apply(response: Response<T>): R {

		if (response.errors().isNullOrEmpty()) {
			val jsonData = gson.toJson(response.data())
			val data: R = gson.fromJson<R>(jsonData, R::class.java)
			return data;
		} else {
			val errors = response.errors()[0]
			println("Code: ${errors.customAttributes()["code"]} \t Message: ${errors.message().toString()}")
			// throw FailedResponseException(errors.customAttributes()["code"].toString().toInt(), errors.message().toString())
			throw Exception("Error apollo")
		}
	}
}
