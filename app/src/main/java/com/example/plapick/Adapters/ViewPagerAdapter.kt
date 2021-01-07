package com.example.plapick.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plapick.CustomViewHolder.ViewPagerImageViewHolder
import com.example.plapick.Models.ViewPagerImageModel
import com.example.plapick.R

class ViewPagerAdapter(var imgList: ArrayList<ViewPagerImageModel>) : RecyclerView.Adapter<ViewPagerImageViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerImageViewHolder {
        return ViewPagerImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hot_place_image_pager, parent, false))
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    override fun onBindViewHolder(holder: ViewPagerImageViewHolder, position: Int) {
        holder.bindWithView(imgList[position])
    }

}