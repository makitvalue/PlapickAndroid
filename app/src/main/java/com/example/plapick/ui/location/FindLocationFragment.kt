package com.example.plapick.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plapick.Adapters.LocationAdapter
import com.example.plapick.Models.LocationModel
import com.example.plapick.MainActivity
import com.example.plapick.R

class FindLocationFragment: Fragment() {

    private lateinit var parentAdapter: LocationAdapter
    private lateinit var childAdapter: LocationAdapter
    val parentLocationList = arrayListOf<LocationModel>()
    val childLocationList = arrayListOf<LocationModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_find_location, container, false)

        for (i in 0..9) {
            if (i == 0) {
                parentLocationList.add(LocationModel(i, "서울${i.toString()}", LocationModel.PARENT, LocationModel.SELECTED))
            } else {
                parentLocationList.add(LocationModel(i, "서울${i.toString()}", LocationModel.PARENT, LocationModel.NO_SELECTED))
                childLocationList.add(LocationModel(i, "강남/역삼/삼성/논현", LocationModel.CHILD, LocationModel.NO_SELECTED))
            }
        }

        parentAdapter = LocationAdapter(parentLocationList)
        childAdapter = LocationAdapter(childLocationList)
        val parentLayoutManager = LinearLayoutManager(context as MainActivity, LinearLayoutManager.VERTICAL, false)
        val childLayoutManager = LinearLayoutManager(context as MainActivity, LinearLayoutManager.VERTICAL, false)
        val rcParent = view.findViewById<RecyclerView>(R.id.rc_parent_location)
        val rcChild = view.findViewById<RecyclerView>(R.id.rc_child_location)

        rcParent.layoutManager = parentLayoutManager
        rcParent.setHasFixedSize(true)
        rcParent.adapter = parentAdapter

        rcChild.layoutManager = childLayoutManager
        rcChild.setHasFixedSize(true)
        rcChild.adapter = childAdapter

        return view
    }
}