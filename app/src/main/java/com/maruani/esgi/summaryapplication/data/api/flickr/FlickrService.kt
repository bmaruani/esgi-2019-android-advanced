package com.maruani.esgi.summaryapplication.data.api.flickr

import com.maruani.esgi.summaryapplication.data.dto.EPhotoDetail
import com.maruani.esgi.summaryapplication.data.dto.EPhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FlickrService {
    @GET("?method=flickr.people.getPublicPhotos")
    fun getPublicPhotos(@Query("user_id") userId: String): Call<EPhotosResponse>

    @GET("?method=flickr.photos.getInfo")
    fun getPhotoInfo(@Query("photo_id") photoId: String): Call<EPhotoDetail>
}