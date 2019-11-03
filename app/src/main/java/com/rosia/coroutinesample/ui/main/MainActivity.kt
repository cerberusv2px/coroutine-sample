package com.rosia.coroutinesample.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rosia.coroutinesample.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

	@Inject
	lateinit var viewModelFactory: MainViewModelFactory

	private lateinit var viewModel: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

		viewModel.fetchPosts()
		viewModel.postUseCase.observe(this, Observer { response ->
			println(response)
		})

		/*viewModel.fetchLocalPost()
		viewModel.postLocalUseCase.observe(this, Observer { response ->
			println("response: $response")
		})*/
	}
}
