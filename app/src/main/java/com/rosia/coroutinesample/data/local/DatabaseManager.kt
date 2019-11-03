package com.rosia.coroutinesample.data.local

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseManager @Inject constructor(var context: Context) {

	private val instance = PostDatabase.getInstance(context)

	fun dropDatabase() {
		instance.clearAllTables()
	}

	fun getInstance() = instance

	fun getUserDao(): PostDao = instance.getPostDao()
}