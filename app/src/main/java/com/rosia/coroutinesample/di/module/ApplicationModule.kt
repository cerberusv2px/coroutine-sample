package com.rosia.coroutinesample.di.module

import android.app.Application
import android.content.Context
import com.rosia.coroutinesample.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module(
	includes = [(PostModule::class)]
)
open class ApplicationModule {

	@Provides
	@PerApplication
	fun provideContext(application: Application): Context = application
}