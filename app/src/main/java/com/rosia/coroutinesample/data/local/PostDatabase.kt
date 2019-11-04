package com.rosia.coroutinesample.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rosia.coroutinesample.data.local.comments.CommentDao
import com.rosia.coroutinesample.data.local.comments.CommentLocalModel
import com.rosia.coroutinesample.data.local.post.PostDao
import com.rosia.coroutinesample.data.local.post.PostLocalModel
import com.rosia.coroutinesample.data.local.postwithcomments.PostWithCommentsDao

@Database(
	entities = [
		(PostLocalModel::class),
		(CommentLocalModel::class)
	],
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
	abstract fun getCommentDao(): CommentDao
	abstract fun getPostWithCommentDao(): PostWithCommentsDao
}