package com.rosia.coroutinesample.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rosia.coroutinesample.domain.PostRepositoryImpl
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
	private val application: Application,
	private val postRepositoryImpl: PostRepositoryImpl

) : ViewModelProvider.Factory {

	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(application, postRepositoryImpl) as T
		}
		throw IllegalArgumentException("Unknown ViewModel class")
	}
}