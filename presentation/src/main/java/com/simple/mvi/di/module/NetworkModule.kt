package com.simple.mvi.di.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.simple.data.services.ApiService
import com.simple.mvi.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single<Cache> {
        Cache(androidContext().cacheDir, 1024)
    }

    single<OkHttpClient> {
        val client = OkHttpClient.Builder()
            .cache(get())
            .connectTimeout(6, TimeUnit.SECONDS)
            .writeTimeout(6, TimeUnit.SECONDS)
            .readTimeout(6, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
        client.build()
    }

    single<Retrofit> {
        val gsonBuilder = GsonBuilder()
        Retrofit.Builder().baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory((GsonConverterFactory.create(gsonBuilder.create())))
            .client(get())
            .build()
    }

    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
}