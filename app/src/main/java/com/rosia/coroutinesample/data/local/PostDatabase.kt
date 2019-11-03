package com.rosia.coroutinesample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
	entities = [(PostLocalModel::class)],
	version = 1,
	exportSchema = false
)
abstract class PostDatabase : RoomDatabase() {

	companion object {
		var instance: PostDatabase? = null
		fun getInstance(context: Context): PostDatabase {
			if (instance == null) {
				instance = createInstance(context)
			}
			return instance as PostDatabase
		}

		private fun createInstance(context: Context): PostDatabase {
			return Room
				.databaseBuilder(context, PostDatabase::class.java, "posts.db")
				.build()
		}
	}

	abstract fun getPostDao(): PostDao
}