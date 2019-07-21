package com.maruani.esgi.summaryapplication.module.architectureComponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maruani.esgi.summaryapplication.data.api.flickr.FlickrProvider
import com.maruani.esgi.summaryapplication.data.model.Photo

class PhotoListViewModel : ViewModel() {

    private val photoList = MutableLiveData<List<Photo>>()
    val photoListLiveData: LiveData<List<Photo>> = photoList


    fun loadPhotoList() {
        FlickrProvider.getPhotos(object : FlickrProvider.Listener<List<Photo>> {
            override fun onSuccess(data: List<Photo>) {
                photoList.value = data
            }

            override fun onError(t: Throwable) {

            }
        })
    }

    fun onItemClick(photo: Photo) {

    }
}