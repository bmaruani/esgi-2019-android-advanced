package com.maruani.esgi.summaryapplication.module.photoDetail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.maruani.esgi.summaryapplication.R
import com.maruani.esgi.summaryapplication.data.model.PhotoDetail
import com.maruani.esgi.summaryapplication.module.common.BaseActivity

class PhotoDetailActivity :
    BaseActivity<PhotoDetailPresenter, PhotoDetailView>(R.layout.activity_photo_detail, PhotoDetailPresenter()),
    PhotoDetailView {

    lateinit var imageView: ImageView
    lateinit var nameTv: TextView
    lateinit var titleTv: TextView
    lateinit var descriptionTv: TextView
    lateinit var tagsTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra(PHOTO_ID)) {
            presenter.setPhotoId(intent.getStringExtra(PHOTO_ID))
        } else {
            finish()
        }

        imageView = findViewById(R.id.activity_photo_detail_imv)
        nameTv = findViewById(R.id.activity_photo_detail_name_tv)
        titleTv = findViewById(R.id.activity_photo_detail_title_tv)
        descriptionTv = findViewById(R.id.activity_photo_detail_description_tv)
        tagsTv = findViewById(R.id.activity_photo_detail_tags_tv)
    }

    override fun updateInfo(photoDetail: PhotoDetail) {
        Glide.with(this).load(photoDetail.url).into(imageView)

        nameTv.text = photoDetail.ownerName
        titleTv.text = photoDetail.title
        descriptionTv.text = photoDetail.description
        tagsTv.text = photoDetail.tags.joinToString(prefix = "#", separator = "  #")
    }

    companion object {
        const val PHOTO_ID = "PhotoDetailActivityPhotoId"
    }
}