package com.example.plapick.ui.location

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.plapick.MainActivity
import com.example.plapick.R

class FindMapFragment: Fragment() {
    private val TAG = "태그"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val view = inflater.inflate(R.layout.fragment_find_map, container, false)
        return view
    }
}