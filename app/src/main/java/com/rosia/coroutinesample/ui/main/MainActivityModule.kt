package com.rosia.coroutinesample.ui.main

import android.app.Application
import com.rosia.coroutinesample.di.scope.PerActivity
import com.rosia.coroutinesample.domain.PostRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

	@Provides
	@PerActivity
	fun provideMainViewModelFactory(
		application: Application,
		postRepositoryImpl: PostRepositoryImpl
	): MainViewModelFactory {
		return MainViewModelFactory(application, postRepositoryImpl)
	}
}