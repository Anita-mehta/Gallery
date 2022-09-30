package com.bhanguz.mvvmhilt.data.remote.retrofit

import com.bhanguz.mvvmhilt.data.local.ImageEntity
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {

    @GET("v2/list")
    suspend fun getImages():Response<List<ImageEntity>>

}