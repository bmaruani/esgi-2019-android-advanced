package com.maruani.esgi.androidadvanced.module.archComponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maruani.esgi.androidadvanced.R
import com.maruani.esgi.androidadvanced.module.recyclerview.simplelist.SimpleListAdapter
import com.maruani.esgi.androidadvanced.module.recyclerview.simplelist.business.model.FlickrPhoto

class PhotoListFragment : Fragment() {

    private lateinit var photoRecyclerView: RecyclerView
    private var simpleListAdapter: SimpleListAdapter? = null
    private lateinit var viewModel: PhotoListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_simple_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoRecyclerView = view.findViewById(R.id.simplelist_photos_recyclerview)

        initRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)

        viewModel.photoListLiveData.observe(viewLifecycleOwner, Observer { dataWrapper ->
            when (dataWrapper) {
                is Success -> updateData(dataWrapper.data)
                //is Failure ->
                //is Loading ->
            }
        })

        viewModel.loadPhotoList()
    }

    private fun initRecyclerView() {
        simpleListAdapter = SimpleListAdapter()
        simpleListAdapter?.setListener(object : SimpleListAdapter.ClickListener {
            override fun onClick(photo: FlickrPhoto) {
                
            }

        })

        photoRecyclerView.adapter = simpleListAdapter
        photoRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun updateData(data: List<FlickrPhoto>) {
        simpleListAdapter?.setData(data)
    }

}