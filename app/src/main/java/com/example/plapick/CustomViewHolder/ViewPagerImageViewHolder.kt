package com.example.plapick.CustomViewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.plapick.Models.ViewPagerImageModel
import com.example.plapick.R
import com.squareup.picasso.Picasso

class ViewPagerImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val image = itemView.findViewById<ImageView>(R.id.iv_hot_place_view_pager_image)

    fun bindWithView(item: ViewPagerImageModel) {
        Picasso.get().load(item.src).into(image)
    }
}