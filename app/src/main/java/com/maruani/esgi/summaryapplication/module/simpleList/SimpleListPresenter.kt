package com.maruani.esgi.summaryapplication.module.simpleList

import androidx.core.app.ActivityOptionsCompat
import com.maruani.esgi.summaryapplication.data.api.flickr.FlickrProvider
import com.maruani.esgi.summaryapplication.data.model.Photo
import com.maruani.esgi.summaryapplication.module.common.BasePresenter


class SimpleListPresenter : BasePresenter<SimpleListView>() {

    override fun onCreate() {
        FlickrProvider.getPhotos(object : FlickrProvider.Listener<List<Photo>> {
            override fun onSuccess(data: List<Photo>) {
                view.updateData(data)
            }

            override fun onError(t: Throwable) {

            }
        })
    }

    fun onItemClick(
        photo: Photo,
        options: ActivityOptionsCompat
    ) {
        navigator.toPhotoDetail(photo.id, options)
    }
}