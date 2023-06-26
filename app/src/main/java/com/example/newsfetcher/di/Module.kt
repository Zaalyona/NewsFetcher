package com.example.newsfetcher.di

import android.util.Log
import androidx.room.Room
import com.example.newsfetcher.AppDatabase
import com.example.newsfetcher.base.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://newsapi.org/"

const val API_KEY = "aa870dbb1db243b2a70a7452f69f3d06"

const val APP_DATABASE = "APP_DATABASE"

val networkModule = module {

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor { message ->
            Log.d("OkHttp", message)
        }.apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addNetworkInterceptor(HeaderInterceptor())
            // устанавливаем время ожидания отклика от сервера
            //обычно ставят время ожидания = 25 сек
            //.callTimeout(100L, TimeUnit.MILLISECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }
}

val databaseModul = module {

    single {
        Room
            .databaseBuilder(androidContext(), AppDatabase::class.java, APP_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().bookmarksDao()
    }
}