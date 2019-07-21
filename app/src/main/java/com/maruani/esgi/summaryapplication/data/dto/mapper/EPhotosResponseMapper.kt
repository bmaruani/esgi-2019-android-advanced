package com.maruani.esgi.summaryapplication.data.dto.mapper

import com.maruani.esgi.summaryapplication.data.dto.EPhotosResponse
import com.maruani.esgi.summaryapplication.data.model.Photo
import com.maruani.esgi.summaryapplication.misc.FlickrUtil

class EPhotosResponseMapper {

    fun map(photosResponse: EPhotosResponse): List<Photo> {
        val ePhotoList = photosResponse.photos.photoList

        return ePhotoList.map {
            val url = FlickrUtil.getPhotoPublicUrl(it.id, it.secret, it.server, it.farm)
            Photo(it.id, url, it.title)
        }
    }
}