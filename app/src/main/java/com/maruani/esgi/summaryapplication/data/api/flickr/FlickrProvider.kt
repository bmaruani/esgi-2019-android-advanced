package com.maruani.esgi.summaryapplication.data.api.flickr

import android.util.Log
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.maruani.esgi.summaryapplication.BuildConfig
import com.maruani.esgi.summaryapplication.data.dto.EPhotoDetail
import com.maruani.esgi.summaryapplication.data.dto.EPhotosResponse
import com.maruani.esgi.summaryapplication.data.dto.mapper.EPhotoDetailMapper
import com.maruani.esgi.summaryapplication.data.dto.mapper.EPhotosResponseMapper
import com.maruani.esgi.summaryapplication.data.model.Photo
import com.maruani.esgi.summaryapplication.data.model.PhotoDetail
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FlickrProvider {
    private val USER_ID = "94993041@N05"

    private var service: FlickrService

    init {
        service = Retrofit.Builder().baseUrl(BuildConfig.FLICKR_API_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickrService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor {
                val request = it.request()
                val url = request.url()
                val builder = url.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.FLICKR_API_KEY)
                    .addQueryParameter("format", BuildConfig.FLICKR_API_FORMAT)
                    .addQueryParameter("nojsoncallback", BuildConfig.FLICKR_API_CALLBACK)

                val newUrl = builder.build()
                val newRequest = request.newBuilder().url(newUrl).build()

                it.proceed(newRequest)
            }.build()
    }

    fun getPhotos(listener: Listener<List<Photo>>) {
        service.getPublicPhotos(USER_ID).enqueue(object : Callback<EPhotosResponse> {
            override fun onFailure(call: Call<EPhotosResponse>, t: Throwable) {
                listener.onError(t)
            }

            override fun onResponse(call: Call<EPhotosResponse>, response: Response<EPhotosResponse>) {

                Log.e("getPhotos", call.request().toString())
                Log.e("getPhotos", response.body().toString())
                response.body()?.let {
                    val photos = EPhotosResponseMapper().map(it)
                    listener.onSuccess(photos)
                }
            }

        })
    }

    fun getPhotoDetail(photoId: String, listener: Listener<PhotoDetail>) {
        service.getPhotoInfo(photoId).enqueue(object : Callback<EPhotoDetail> {
            override fun onFailure(call: Call<EPhotoDetail>, t: Throwable) {
                listener.onError(t)
            }

            override fun onResponse(call: Call<EPhotoDetail>, response: Response<EPhotoDetail>) {
                response.body()?.let {
                    val photoDetail = EPhotoDetailMapper().map(it)
                    listener.onSuccess(photoDetail)
                }
            }

        })
    }

    interface Listener<T> {
        fun onSuccess(data: T)
        fun onError(t: Throwable)
    }
}

