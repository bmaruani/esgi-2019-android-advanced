package com.maruani.esgi.summaryapplication.module.photoDetail

import com.maruani.esgi.summaryapplication.data.api.flickr.FlickrProvider
import com.maruani.esgi.summaryapplication.data.model.PhotoDetail
import com.maruani.esgi.summaryapplication.module.common.BasePresenter

class PhotoDetailPresenter : BasePresenter<PhotoDetailView>() {
    private var photoId: String? = null

    fun setPhotoId(photoId: String) {
        this.photoId = photoId
    }

    override fun onResume() {
        super.onResume()
        photoId?.let {
            FlickrProvider.getPhotoDetail(it, object: FlickrProvider.Listener<PhotoDetail> {
                override fun onSuccess(data: PhotoDetail) {
                    view.updateInfo(data)
                }

                override fun onError(t: Throwable) {

                }

            })
        }
    }

}