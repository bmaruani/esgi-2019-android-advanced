package com.maruani.esgi.summaryapplication.module.architectureComponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maruani.esgi.summaryapplication.data.api.flickr.FlickrProvider
import com.maruani.esgi.summaryapplication.data.model.PhotoDetail

class PhotoDetailViewModel : ViewModel() {

    private val photo = MutableLiveData<PhotoDetail>()
    val photoLiveData: LiveData<PhotoDetail> = photo

    fun getPhoto(id: String) {
        FlickrProvider.getPhotoDetail(id, object : FlickrProvider.Listener<PhotoDetail> {
            override fun onSuccess(data: PhotoDetail) {
                photo.value = data
            }

            override fun onError(t: Throwable) {

            }

        })
    }
}