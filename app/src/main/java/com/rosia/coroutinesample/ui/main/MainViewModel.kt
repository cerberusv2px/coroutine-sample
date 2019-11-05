package com.rosia.coroutinesample.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rosia.coroutinesample.data.local.postwithcomments.PostWithComments
import com.rosia.coroutinesample.data.remote.PostRemoteModel
import com.rosia.coroutinesample.data.remote.apollo.ResultModel
import com.rosia.coroutinesample.domain.PostRepositoryImpl
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
	application: Application,
	private val repository: PostRepositoryImpl
) : AndroidViewModel(application) {

	private val loadingUseCase = MutableLiveData<Boolean>()
	private val errorUseCase = MutableLiveData<String?>()
	private val postRemoteUseCase = MutableLiveData<List<PostRemoteModel>>()
	private val postLocalUseCase = MutableLiveData<List<PostWithComments>>()
	private val rickmortyRemoteUseCase = MutableLiveData<List<ResultModel>>()

	val spinner: LiveData<Boolean>
		get() = loadingUseCase

	val postRemoteResponse: LiveData<List<PostRemoteModel>>
		get() = postRemoteUseCase

	val postLocalResponse: LiveData<List<PostWithComments>>
		get() = postLocalUseCase

	val errorMessage: LiveData<String?>
		get() = errorUseCase

	val rickMortyResponse: LiveData<List<ResultModel>>
		get() = rickmortyRemoteUseCase

	public override fun onCleared() {
		viewModelScope.cancel()
		super.onCleared()
	}

	fun onErrorShown() {
		errorUseCase.value = null
	}

	fun fetchPosts() {
		viewModelScope.launch {
			try {
				loadingUseCase.value = true
				val postList = repository.fetchPosts()
				postRemoteUseCase.value = postList
			} catch (error: Exception) {
				errorUseCase.value = error.message ?: "Something went wrong"
			} finally {
				loadingUseCase.value = false
			}
		}
	}

	fun fetchLocalPost(): LiveData<List<PostWithComments>> {
		return repository.getPostWithComments()
	}

	fun updatePosts(title: String, id: Int) {
		viewModelScope.launch {
			repository.updatePost(title, id)
		}
	}

	fun fetchRickMortyData() {
		viewModelScope.launch {
			try {
				loadingUseCase.value = true
				val postList = repository.fetchRickAndMortyData()
				rickmortyRemoteUseCase.value = postList
			} catch (error: Exception) {
				errorUseCase.value = error.message ?: "Something went wrong"
			} finally {
				loadingUseCase.value = false
			}
		}
	}
}