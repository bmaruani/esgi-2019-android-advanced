package com.maruani.esgi.summaryapplication.module.photoDetail

import com.maruani.esgi.summaryapplication.data.model.PhotoDetail
import com.maruani.esgi.summaryapplication.module.common.BaseView

interface PhotoDetailView : BaseView {
    fun updateInfo(photoDetail: PhotoDetail)
}