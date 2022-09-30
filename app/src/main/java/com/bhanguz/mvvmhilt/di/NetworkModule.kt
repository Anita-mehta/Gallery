package com.bhanguz.mvvmhilt.di

import com.bhanguz.mvvmhilt.data.remote.retrofit.ImageService
import com.bhanguz.mvvmhilt.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
     fun providesApi(retrofit:Retrofit): ImageService{
     return retrofit.create(ImageService::class.java)
}
}