package com.maruani.esgi.summaryapplication.module.simpleList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.maruani.esgi.summaryapplication.R
import com.maruani.esgi.summaryapplication.data.model.Photo

class SimpleListAdapter : RecyclerView.Adapter<SimpleListAdapter.SimpleListViewHolder>() {

    private var data: List<Photo>? = null
    private var listener: ClickListener? = null

    override fun getItemCount() = data?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SimpleListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_simple_photo, parent, false))


    override fun onBindViewHolder(holder: SimpleListViewHolder, position: Int) {
        data!![position].also { photo ->
            holder.titleTv.text = photo.title
            Glide.with(holder.itemView)
                .load(photo.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.thumbnailImv)

            holder.itemView.setOnClickListener { listener?.onClick(photo,  holder.thumbnailImv, holder.titleTv) }
        }
    }

    fun setData(data: List<Photo>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setListener(listener: ClickListener) {
        this.listener = listener
    }


    class SimpleListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var thumbnailImv: ImageView = itemView.findViewById(R.id.item_simple_photo_imv)
        var titleTv: TextView = itemView.findViewById(R.id.item_simple_photo_tv)
    }

    interface ClickListener {
        fun onClick(photo: Photo, thumbnail : ImageView, title : TextView)
    }
}