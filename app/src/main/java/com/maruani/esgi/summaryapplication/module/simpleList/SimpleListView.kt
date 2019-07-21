package com.maruani.esgi.summaryapplication.module.simpleList

import com.maruani.esgi.summaryapplication.data.model.Photo
import com.maruani.esgi.summaryapplication.module.common.BaseView

interface SimpleListView : BaseView {
    fun updateData(data: List<Photo>)
}