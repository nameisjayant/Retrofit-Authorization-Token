package com.example.myapplication.di

import com.example.myapplication.data.network.ApiService
import com.example.myapplication.data.network.OAuthInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi
        .Builder()
        .run {
            add(KotlinJsonAdapterFactory())
                .build()
        }

    @Provides
    @Singleton
    fun providesApiService(moshi: Moshi, okHttpClient: OkHttpClient): ApiService =
        Retrofit
            .Builder()
            .run {
                baseUrl(ApiService.BASE_URL)
                addConverterFactory(MoshiConverterFactory.create(moshi))
                client(okHttpClient)
                build()
            }.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(
            OAuthInterceptor(
                "Qc0VB0-vu-Szor6VgydL405FqFAvAmvjtMZnzT7EHyXSaMnd6ZucTmUoi6JRhDSsIc0OnK0ufDlJkVfE0fhkpFda6yC3yoSgPORh43ejBHkwSrHCIUVI_LZZSJe62Iwah6IlCUybqDWyLNIcm-FEdZmxyfOgQDfE--7qeC-TChmnMmpt0AOwv9c4VVd9VF7XxxYo2rzc5mg0PI9P0mHTKUOXjDRg0Dmdm-yNGau_5ecAml1JncBQNvPX4X4u5BfL1H5OU43tSqvw7THsVS4dAgLmaMKIqi3RYlg7jTfwKwt_id1h_YjqMISOckmGs9L0",
                "Bearer"
            )
        )
            .addInterceptor(interceptor)
            .build()
    }
}