package com.example.plapick

import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.plapick.ui.home.HomeFragment
import com.example.plapick.ui.location.FindLocationFragment
import com.example.plapick.ui.location.FindMapFragment
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private val TAG = "태그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_location, R.id.navigation_upload, R.id.navigation_temp, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val display = windowManager.defaultDisplay
//        val size = Point()
//        display.getRealSize(size)
//        val width = size.x
//        val height = size.y

    }



    public fun setFrag(fragNum: Int) {
        val ft = supportFragmentManager.beginTransaction()
        when(fragNum)
        {
            0 -> {
                //지역별
                ft.replace(R.id.fl_find_location_wrapper, FindLocationFragment()).commit()
            }
            1 -> {
                //지도로 찾기
                ft.replace(R.id.fl_find_location_wrapper, FindMapFragment()).commit()
            }
        }
    }

    public fun setHeader(text: String, searchYN: Boolean) {
        findViewById<TextView>(R.id.tv_header_title).setText(text)
        if (searchYN) {
            findViewById<ImageButton>(R.id.ib_header_search_btn).visibility = View.VISIBLE
        } else {
            findViewById<ImageButton>(R.id.ib_header_search_btn).visibility = View.INVISIBLE
        }
    }
}