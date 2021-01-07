package com.example.plapick.ui.upload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.plapick.MainActivity
import com.example.plapick.R

class UploadFragment : Fragment() {

    private lateinit var locationViewModel: UploadViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        locationViewModel =
                ViewModelProvider(this).get(UploadViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_upload, container, false)
        val textView: TextView = root.findViewById(R.id.text_upload)
        locationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val mainActivity = (activity as MainActivity)
        mainActivity.setHeader("새로운 게시물", false)

        return root
    }
}