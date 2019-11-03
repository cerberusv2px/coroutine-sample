package com.rosia.coroutinesample.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rosia.coroutinesample.data.local.DatabaseManager
import com.rosia.coroutinesample.data.remote.ApiService
import com.rosia.coroutinesample.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {

	@Provides
	@PerApplication
	fun provideGson(): Gson = GsonBuilder().setLenient().create()

	@Provides
	@PerApplication
	fun provideHttpLogginInterceptor(): HttpLoggingInterceptor {
		val logging = HttpLoggingInterceptor()
		logging.level = HttpLoggingInterceptor.Level.BODY
		return logging
	}

	@Provides
	@PerApplication
	fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
		return OkHttpClient.Builder()
			.apply {
				addInterceptor(httpLoggingInterceptor)
				connectTimeout(180, TimeUnit.SECONDS)
				readTimeout(180, TimeUnit.SECONDS)
			}
			.build()
	}

	@Provides
	@PerApplication
	fun provideApiService(okHttpClient: OkHttpClient, gson: Gson): ApiService {
		val retrofit = Retrofit.Builder()
			.baseUrl("http://jsonplaceholder.typicode.com/")
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build()

		return retrofit.create(ApiService::class.java)
	}


	@Provides
	@PerApplication
	fun provideDatabaseManager(context: Context): DatabaseManager {
		return DatabaseManager(context)
	}
}