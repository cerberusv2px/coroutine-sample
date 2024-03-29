package com.rosia.coroutinesample.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.rosia.coroutinesample.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

	@Inject
	lateinit var viewModelFactory: MainViewModelFactory

	private lateinit var viewModel: MainViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

/*		viewModel.fetchLocalPost().observe(this, Observer { response ->
			textView.visibility = View.VISIBLE
			progressBar.visibility = View.INVISIBLE
			textView.text = response[0].post.title
		})*/

		viewModel.fetchPosts()
		viewModel.postRemoteResponse.observe(this, Observer { response ->
			// println("Response: $response")
			Toast.makeText(this, "Size: ${response.size}", Toast.LENGTH_LONG).show()
		})

		viewModel.errorMessage.observe(this, Observer { message ->
			message?.let {
				Snackbar.make(rootLayout, message, Snackbar.LENGTH_LONG).show()
				viewModel.onErrorShown()
			}
		})

		viewModel.spinner.observe(this, Observer { show ->
			textView.visibility = if (!show) View.VISIBLE else View.INVISIBLE
			progressBar.visibility = if (show) View.VISIBLE else View.INVISIBLE
		})

		button.setOnClickListener {
			viewModel.updatePosts("testing only", 1)
		}
	}

	override fun onDestroy() {
		viewModel.onCleared()
		super.onDestroy()
	}

	override fun onResume() {
		super.onResume()
		// viewModel.fetchPosts()
	}
}
