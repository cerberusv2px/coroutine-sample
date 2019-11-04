package com.rosia.coroutinesample.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rosia.coroutinesample.data.local.PostLocalModel
import com.rosia.coroutinesample.data.remote.PostRemoteModel
import com.rosia.coroutinesample.domain.PostRepositoryImpl
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
	application: Application,
	private val postRepositoryImpl: PostRepositoryImpl
) : AndroidViewModel(application) {

	private val _postUseCase = MutableLiveData<List<PostRemoteModel>>()
	private val _postLocalUseCase = MutableLiveData<List<PostLocalModel>>()
	private val _spinner = MutableLiveData<Boolean>()
	private val _errorMessage = MutableLiveData<String?>()

	val spinner: LiveData<Boolean>
		get() = _spinner

	val postRemoteResponse: LiveData<List<PostRemoteModel>>
		get() = _postUseCase

	val postLocalResponse: LiveData<List<PostLocalModel>>
		get() = _postLocalUseCase

	val errorMessage: LiveData<String?>
		get() = _errorMessage

	public override fun onCleared() {
		viewModelScope.cancel()
		super.onCleared()
	}

	fun onErrorShown() {
		_errorMessage.value = null
	}

	fun fetchPosts() {
		viewModelScope.launch {
			try {
				_spinner.value = true
				val postList = postRepositoryImpl.fetchPosts()
				_postUseCase.value = postList
			} catch (error: Exception) {
				_errorMessage.value = error.message ?: "Something went wrong"
			} finally {
				_spinner.value = false
			}

		}
	}

	fun fetchLocalPost() {
		viewModelScope.launch {
			val postList = postRepositoryImpl.getAllPosts()
			_postLocalUseCase.value = postList
		}
	}
}