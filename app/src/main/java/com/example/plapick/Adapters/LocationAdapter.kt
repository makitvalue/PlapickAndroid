package com.example.plapick.Adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.plapick.Models.LocationModel
import com.example.plapick.R

class LocationAdapter(val locationList: ArrayList<LocationModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = "태그"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View?
        return when(viewType) {
            LocationModel.PARENT -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.parent_location_list_item, parent, false)
                parentHolder(view)
            }
            LocationModel.CHILD -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.child_location_list_item, parent, false)
                childHolder(view)
            }
            else -> throw RuntimeException("알 수 없는 뷰 타입 에러")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = locationList[position]

        when (item.type) {
            LocationModel.PARENT -> {
                (holder as parentHolder).name.text = item.name
                if (item.status == LocationModel.SELECTED) {
                    holder.itemView.setBackgroundColor((Color.parseColor("#FFFFFF")))
                    holder.name.setTextColor(Color.parseColor("#333333"))

                    locationList.forEach {
                        Log.d(TAG, "${it.id.toString()}의 STATUS : ${it.status.toString()}")
                    }

                }
            }
            LocationModel.CHILD -> {
                (holder as childHolder).name.text = item.name
            }
            else -> {
                throw RuntimeException("알 수 없는 뷰 타입 에러")
            }
        }

        holder.itemView.setOnClickListener {
            if (item.type == LocationModel.PARENT) {
                locationList.forEach {
                    it.status = LocationModel.NO_SELECTED
                }

                item.status = LocationModel.SELECTED
                notifyDataSetChanged()

//                it.setBackgroundColor((Color.parseColor("#FFFFFF")))
//                (holder as parentHolder).name.setTextColor(Color.parseColor("#333333"))
            }
        }

    }

    override fun getItemCount(): Int {
        return locationList.size
    }

    override fun getItemViewType(position: Int): Int {
        return locationList[position].type
    }

    inner class parentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_parent_location)
    }

    inner class childHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_child_location)
    }
}