package com.example.plapick.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.plapick.R


class HotPlaceHeader @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0)
    : ConstraintLayout(context, attrs, defStyleAttr){

    lateinit var hotTitle: TextView
    lateinit var hotCategory: TextView
    lateinit var hotAddress: TextView
    lateinit var hotLikeCnt: TextView
    lateinit var hotPhotoCnt: TextView


    init {
        inflate(context, R.layout.hot_place_header, this)
        hotTitle = findViewById(R.id.tv_hot_place_header_title)
        hotCategory = findViewById(R.id.tv_hot_place_header_category)
        hotAddress = findViewById(R.id.tv_hot_place_header_address)
        hotLikeCnt = findViewById(R.id.tv_hot_place_header_like_cnt)
        hotPhotoCnt = findViewById(R.id.tv_hot_place_header_photo_cnt)
    }

    fun setTitle(title:String) {
        hotTitle?.setText(title)
    }

    fun setCategory(category: String) {
        hotCategory?.setText(category)
    }
    fun setAddress(address: String) {
        hotAddress?.setText(address)
    }
    fun setLikeCnt(likeCnt: Int) {
        hotLikeCnt?.setText(likeCnt.toString())
    }
    fun setPhotoCnt(photoCnt: Int) {
        hotPhotoCnt?.setText(photoCnt.toString())
    }


}