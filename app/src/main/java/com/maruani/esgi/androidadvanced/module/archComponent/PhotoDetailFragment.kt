package com.maruani.esgi.androidadvanced.module.archComponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.maruani.esgi.androidadvanced.R
import com.maruani.esgi.androidadvanced.module.recyclerview.simplelist.business.model.FlickrPhotoDetail

class PhotoDetailFragment : Fragment() {
    lateinit var imageView: ImageView
    lateinit var nameTv: TextView
    lateinit var titleTv: TextView
    lateinit var descriptionTv: TextView
    lateinit var tagsTv: TextView

    private lateinit var viewModel: PhotoDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_photo_detail, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view) {
            imageView = findViewById(R.id.activity_photo_detail_imv)
            nameTv = findViewById(R.id.activity_photo_detail_name_tv)
            titleTv = findViewById(R.id.activity_photo_detail_title_tv)
            descriptionTv = findViewById(R.id.activity_photo_detail_description_tv)
            tagsTv = findViewById(R.id.activity_photo_detail_tags_tv)
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PhotoDetailViewModel::class.java)
        viewModel.photoLiveData.observe(viewLifecycleOwner, Observer {
            updateInfo(it)
        })

    }

    private fun updateInfo(photoDetail: FlickrPhotoDetail) {
        Glide.with(this).load(photoDetail.url).transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
        titleTv.text = photoDetail.title
        descriptionTv.text = photoDetail.description
        tagsTv.text = photoDetail.tags.joinToString(prefix = "#", separator = "  #")
    }
}