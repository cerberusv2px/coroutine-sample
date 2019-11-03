package com.rosia.coroutinesample

import android.app.Activity
import android.app.Application
import com.rosia.coroutinesample.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CoroutineApplication : Application(), HasActivityInjector {

	@Inject
	lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	override fun activityInjector(): AndroidInjector<Activity> {
		return activityDispatchingAndroidInjector
	}

	override fun onCreate() {
		super.onCreate()
		initializeDaggerComponent()
	}

	private fun initializeDaggerComponent() {
		DaggerApplicationComponent.builder()
			.application(this)
			.build()
			.inject(this)
	}
}