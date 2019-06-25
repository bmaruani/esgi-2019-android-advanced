package com.maruani.esgi.androidadvanced.module.archComponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maruani.esgi.androidadvanced.module.recyclerview.simplelist.business.api.FlickrProvider
import com.maruani.esgi.androidadvanced.module.recyclerview.simplelist.business.model.FlickrPhotoDetail

class PhotoDetailViewModel : ViewModel() {

    private val photo = MutableLiveData<FlickrPhotoDetail>()
    val photoLiveData: LiveData<FlickrPhotoDetail> = photo

    fun getPhoto(id: String) {
        FlickrProvider.getPhotoDetail(id, object : FlickrProvider.Listener<FlickrPhotoDetail> {
            override fun onSuccess(data: FlickrPhotoDetail) {
                photo.value = data
            }

            override fun onError(t: Throwable) {

            }

        })
    }
}