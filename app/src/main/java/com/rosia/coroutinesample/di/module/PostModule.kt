package com.rosia.coroutinesample.di.module

import com.rosia.coroutinesample.data.local.DatabaseManager
import com.rosia.coroutinesample.data.local.PostLocalImpl
import com.rosia.coroutinesample.data.remote.ApiService
import com.rosia.coroutinesample.data.remote.PostRemoteImpl
import com.rosia.coroutinesample.domain.PostRepository
import com.rosia.coroutinesample.domain.PostRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class PostModule {

	@Provides
	fun providePostRemote(
		apiService: ApiService
	): PostRepository.Remote {
		return PostRemoteImpl(apiService)
	}

	@Provides
	fun providePostLocal(
		databaseManager: DatabaseManager
	): PostRepository.Local {
		return PostLocalImpl(databaseManager)
	}

	@Provides
	fun providePostRepository(
		local: PostRepository.Local,
		remote: PostRepository.Remote
	): PostRepositoryImpl {
		return PostRepositoryImpl(local, remote)
	}
}