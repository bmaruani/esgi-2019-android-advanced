package com.maruani.esgi.androidadvanced.module.archComponent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maruani.esgi.androidadvanced.module.recyclerview.simplelist.business.api.FlickrProvider
import com.maruani.esgi.androidadvanced.module.recyclerview.simplelist.business.model.FlickrPhoto

class PhotoListViewModel : ViewModel() {

    private val photoListMutableLiveData = MutableLiveData<DataWrapper<List<FlickrPhoto>>>()
    val photoListLiveData: LiveData<DataWrapper<List<FlickrPhoto>>> = photoListMutableLiveData

    fun loadPhotoList() {
        photoListMutableLiveData.setLoadingState(true)
        FlickrProvider.getPhotos(object : FlickrProvider.Listener<List<FlickrPhoto>> {
            override fun onSuccess(data: List<FlickrPhoto>) {
                photoListMutableLiveData.setLoadingState(false)
                photoListMutableLiveData.value = Success(data)
            }

            override fun onError(t: Throwable) {
                photoListMutableLiveData.setLoadingState(false)
                photoListMutableLiveData.value = Failure(t)
            }
        })
    }

}