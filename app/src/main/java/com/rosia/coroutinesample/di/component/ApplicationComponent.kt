package com.rosia.coroutinesample.di.component

import android.app.Application
import com.rosia.coroutinesample.CoroutineApplication
import com.rosia.coroutinesample.di.module.ApiModule
import com.rosia.coroutinesample.di.module.ApplicationModule
import com.rosia.coroutinesample.di.provider.ActivityBindingModule
import com.rosia.coroutinesample.di.scope.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
	modules = [
		(ActivityBindingModule::class),
		(ApiModule::class),
		(ApplicationModule::class),
		(AndroidSupportInjectionModule::class)
	]
)
interface ApplicationComponent {

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application: Application): Builder

		fun build(): ApplicationComponent
	}

	fun inject(app: CoroutineApplication)
}