package com.maruani.esgi.summaryapplication.data.dto.mapper

import com.maruani.esgi.summaryapplication.data.dto.EPhotoDetail
import com.maruani.esgi.summaryapplication.data.model.PhotoDetail
import com.maruani.esgi.summaryapplication.misc.FlickrUtil

class EPhotoDetailMapper {
    fun map(ePhotoDetail: EPhotoDetail): PhotoDetail {
        return ePhotoDetail.photo.run {
            val url = FlickrUtil.getPhotoPublicUrl(id, secret, server, farm)
            val tags = tags.tag.map { it.raw }

            PhotoDetail(id, url, title.content, description.content, owner.username, owner.realname, tags)
        }

    }
}