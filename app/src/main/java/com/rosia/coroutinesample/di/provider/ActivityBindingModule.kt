package com.rosia.coroutinesample.di.provider

import com.rosia.coroutinesample.di.scope.PerActivity
import com.rosia.coroutinesample.ui.main.MainActivity
import com.rosia.coroutinesample.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

	@PerActivity
	@ContributesAndroidInjector(modules = [(MainActivityModule::class)])
	abstract fun bindMainActivity(): MainActivity
}
