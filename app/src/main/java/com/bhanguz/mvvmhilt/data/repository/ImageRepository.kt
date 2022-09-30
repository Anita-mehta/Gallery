package com.bhanguz.mvvmhilt.data.repository

import com.bhanguz.mvvmhilt.data.local.ImageEntity
import com.bhanguz.mvvmhilt.data.remote.retrofit.ImageService
import com.bhanguz.mvvmhilt.utils.Resources
import javax.inject.Inject

class ImageRepository @Inject constructor(private val api:ImageService) {
    suspend fun getImage(): Resources<List<ImageEntity>?> {
        val response = api.getImages()
        return if (response.isSuccessful) {
            Resources.Success(response.body())
        } else {
            Resources.Error(response.message())
        }
    }
}