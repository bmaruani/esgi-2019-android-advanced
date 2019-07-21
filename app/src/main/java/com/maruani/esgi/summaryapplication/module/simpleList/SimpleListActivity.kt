package com.maruani.esgi.summaryapplication.module.simpleList

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maruani.esgi.summaryapplication.R
import com.maruani.esgi.summaryapplication.data.model.Photo
import com.maruani.esgi.summaryapplication.module.common.BaseActivity


class SimpleListActivity :
    BaseActivity<SimpleListPresenter, SimpleListView>(R.layout.activity_simple_list, SimpleListPresenter()),
    SimpleListView {

    private lateinit var photoRecyclerView: RecyclerView

    private var simpleListAdapter: SimpleListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoRecyclerView = findViewById(R.id.activity_simplelist_recyclerview)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        simpleListAdapter = SimpleListAdapter()
        simpleListAdapter?.setListener(object : SimpleListAdapter.ClickListener {
            override fun onClick(photo: Photo, thumbnail: ImageView, title: TextView) {
                //val options = prepareOptionsForOneElement(thumbnail);
                val options = prepareOptionsForMultipleElement(thumbnail, title)
                presenter.onItemClick(photo, options)
            }
        })
        photoRecyclerView.adapter = simpleListAdapter
        photoRecyclerView.layoutManager = LinearLayoutManager(this)
        photoRecyclerView.layoutAnimation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_from_right)
    }

    private fun prepareOptionsForOneElement(thumbnail: ImageView): ActivityOptionsCompat {
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
            this@SimpleListActivity,
            thumbnail as View,
            "pictureCity"
        )
    }

    private fun prepareOptionsForMultipleElement(thumbnail: ImageView, title: TextView): ActivityOptionsCompat {
        val pairPicture = Pair.create(thumbnail as View, "pictureCity")
        val pairTitle = Pair.create(title as View, "titleCity")
        return ActivityOptionsCompat.makeSceneTransitionAnimation(this@SimpleListActivity, pairPicture, pairTitle)
    }

    override fun updateData(data: List<Photo>) {
        simpleListAdapter?.setData(data)
    }
}
