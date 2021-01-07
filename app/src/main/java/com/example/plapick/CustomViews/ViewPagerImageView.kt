package com.example.plapick.CustomViews

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.plapick.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ViewPagerImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr: Int=0)
    : LinearLayout(context, attrs, defStyleAttr){

    init {
        inflate(context, R.layout.hot_place_image_pager, this)
        val viewPager = findViewById<ViewPager2>(R.id.vp_image_viewpager)
        val indicator = findViewById<DotsIndicator>(R.id.dots_indicator)
    }


}