package com.maruani.esgi.summaryapplication.module.architectureComponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maruani.esgi.summaryapplication.R
import com.maruani.esgi.summaryapplication.data.model.Photo
import com.maruani.esgi.summaryapplication.module.simpleList.SimpleListAdapter

class PhotoListFragment : Fragment() {

    private lateinit var viewModel: PhotoListViewModel
    private lateinit var photoRecyclerView: RecyclerView
    private var simpleListAdapter: SimpleListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_simple_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoRecyclerView = view.findViewById(R.id.activity_simplelist_recyclerview)
        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)
        viewModel.photoListLiveData.observe(viewLifecycleOwner, Observer {
            updateData(it)
        })

        viewModel.loadPhotoList()
    }

    private fun initRecyclerView() {
        simpleListAdapter = SimpleListAdapter()
        simpleListAdapter?.setListener(object : SimpleListAdapter.ClickListener {
            override fun onClick(photo: Photo, thumbnail: ImageView, title: TextView) {
                view?.also {
                    val actionDetail =
                        PhotoListFragmentDirections.actionPhotoListFragmentToPhotoDetailFragment(photo.id)
                    Navigation.findNavController(it).navigate(actionDetail)
                }
            }
        })
        photoRecyclerView.adapter = simpleListAdapter
        photoRecyclerView.layoutManager = LinearLayoutManager(context)
        photoRecyclerView.layoutAnimation =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_right)
    }

    private fun updateData(data: List<Photo>) {
        simpleListAdapter?.setData(data)
    }
}