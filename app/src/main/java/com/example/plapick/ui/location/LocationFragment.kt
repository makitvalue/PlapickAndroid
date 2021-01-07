package com.example.plapick.ui.location

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.plapick.MainActivity
import com.example.plapick.R

class LocationFragment : Fragment() {

    private val TAG = "태그"
    private lateinit var locationViewModel: LocationViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        locationViewModel =
                ViewModelProvider(this).get(LocationViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_location, container, false)

        val mainActivity = (activity as MainActivity)
        val findToLocationBtn = root.findViewById<TextView>(R.id.tv_find_to_location_btn)
        val findToMapBtn = root.findViewById<TextView>(R.id.tv_find_to_map_btn)

        mainActivity.setFrag(0)
        mainActivity.setHeader("지역으로 찾기", false)

        //지역으로 찾기
        findToLocationBtn.setOnClickListener {
//            findToLocationBtn.setTypeface(null, Typeface.BOLD)
            findToMapBtn.setTextColor(Color.parseColor("#999999"))
            findToLocationBtn.setTextColor(Color.parseColor("#000000"))
            mainActivity.setFrag(0)

        }
        //지도로 찾기
        findToMapBtn.setOnClickListener {
//            findToMapBtn.setTypeface(null, Typeface.BOLD)
            findToLocationBtn.setTextColor(Color.parseColor("#999999"))
            findToMapBtn.setTextColor(Color.parseColor("#000000"))
            Log.d(TAG, "click 지도로찾기")
            mainActivity.setFrag(1)

        }


        return root
    }
}