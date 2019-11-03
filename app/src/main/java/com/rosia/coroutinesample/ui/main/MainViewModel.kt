package com.rosia.coroutinesample.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rosia.coroutinesample.data.local.PostLocalModel
import com.rosia.coroutinesample.data.remote.PostRemoteModel
import com.rosia.coroutinesample.domain.PostRepositoryImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
	application: Application,
	private val postRepositoryImpl: PostRepositoryImpl
) : AndroidViewModel(application) {

	val postUseCase = MutableLiveData<List<PostRemoteModel>>()
	val postLocalUseCase = MutableLiveData<List<PostLocalModel>>()

	fun fetchPosts() {
		viewModelScope.launch {
			val postList = postRepositoryImpl.fetchPosts()
			postUseCase.value = postList
		}
	}

	fun fetchLocalPost() {
		viewModelScope.launch {
			val postList = postRepositoryImpl.getAllPosts()
			postLocalUseCase.value = postList
		}
	}
}