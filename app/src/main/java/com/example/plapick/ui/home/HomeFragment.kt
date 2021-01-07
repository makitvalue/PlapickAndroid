package com.example.plapick.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.plapick.*
import com.example.plapick.Adapters.ViewPagerAdapter
import com.example.plapick.CustomViews.HotPlaceHeader
import com.example.plapick.CustomViews.ViewPagerImageView
import com.example.plapick.Models.HotPlaceHeaderModel
import com.example.plapick.Models.RecentPickModel
import com.example.plapick.Models.ViewPagerImageModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    var hotPlaceImageList = ArrayList<ViewPagerImageModel>()


    private val TAG = "태그"
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val swipe: SwipeRefreshLayout = root.findViewById(R.id.sl_swipe_home)

        swipe.setOnRefreshListener {
            Log.d(TAG, "SWIPE!!")
            swipe.isRefreshing = false
        }

        val mainActivity = (activity as MainActivity)
        mainActivity.setHeader("PLAPICK", true)

//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        var realWidth = 0
        var realHeight = 0
        val mainContainer: ConstraintLayout = root.findViewById(R.id.cl_home_wrapper)
        //xml 이 로드 되고 난 후 width값 계산
        mainContainer.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                realWidth = mainContainer.width
                realHeight = mainContainer.height
                mainContainer.viewTreeObserver.removeOnGlobalLayoutListener(this)
                Log.d(TAG, "width: ${realWidth.toString()} ")

                var image1:String = "https://search.pstatic.net/common/?autoRotate=true&quality=95&size=168x130&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20201219_273%2F1608310531105LbSPn_JPEG%2Fxy1eVk2y7x_3PoHjBBqRogeD.jpg&type=f"
                var image2:String = "https://search.pstatic.net/common/?autoRotate=true&quality=95&size=168x130&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20201219_15%2F1608310547825nlqll_JPEG%2Fr-69nXbPJyhQEgMaASM-TnyT.jpg&type=f"
                var image3:String = "https://search.pstatic.net/common/?autoRotate=true&quality=95&size=168x130&src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20201219_137%2F1608311341550nl8aU_JPEG%2FVmvsL09h05YySk0McpOjLqjM.jpg&type=f"

                //Recent Pick
                val recentParent = root.findViewById<LinearLayout>(R.id.ll_home_recent_pick)
                recentParent.addView(createType1Layout(realWidth, RecentPickModel(image1, image2, image3)))
                recentParent.addView(createType2Layout(realWidth, RecentPickModel(image1, image2, image3)))
                recentParent.addView(createType1Layout(realWidth, RecentPickModel(image1, image2, image3)))
                recentParent.addView(createType3Layout(realWidth, RecentPickModel(image1, image2, image3)))
                recentParent.addView(createType1Layout(realWidth, RecentPickModel(image1, image2, image3)))

                //HotPlace
                val hotParent = root.findViewById<LinearLayout>(R.id.ll_home_hot_place)
                hotParent.addView(createHotHeader(HotPlaceHeaderModel("타이틀1", "관광, 명소 - 수목원,식물원",  "경기 가평군 상면 수목원로 432", 100, 100)))
                hotParent.addView(createHotHeader(HotPlaceHeaderModel("타이틀2", "관광, 명소 - 수목원,식물원",  "주소 주소 주소", 200, 200)))
                hotPlaceImageList.add(ViewPagerImageModel(image1))
                hotPlaceImageList.add(ViewPagerImageModel(image2))
                hotPlaceImageList.add(ViewPagerImageModel(image3))


                viewPagerAdapter = ViewPagerAdapter(hotPlaceImageList)

                var pager = createHotImageViewPager()
                pager.findViewById<ViewPager2>(R.id.vp_image_viewpager).apply {
                    adapter = viewPagerAdapter
                    orientation = ViewPager2.ORIENTATION_HORIZONTAL
                }

                hotParent.addView(pager)

            }
        })
        return root
    }

    fun createHotImageViewPager() : LinearLayout {
        val pager = ViewPagerImageView(context as MainActivity)

        return pager
    }

    fun createHotHeader(hotModel: HotPlaceHeaderModel) : ConstraintLayout {
        val header = HotPlaceHeader(context as MainActivity)
        header.setTitle(hotModel.title)
        header.setCategory(hotModel.category)
        header.setAddress(hotModel.address)
        header.setLikeCnt(hotModel.likeCnt)
        header.setPhotoCnt(hotModel.photoCnt)

        return header
    }



    fun createType1Layout(width: Int, recentModel: RecentPickModel): LinearLayout {

        var imgWidth = (width - 4) / 3
        var imgHeight = imgWidth


        var parentLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        parentLayoutParams.setMargins(0, 0, 0, 2)

        var commonImgView1Params = LinearLayout.LayoutParams(imgWidth, imgHeight)
        var commonImgView2Params = LinearLayout.LayoutParams(imgWidth, imgHeight)
        var marginImgViewParams = LinearLayout.LayoutParams(imgWidth, imgHeight)

        marginImgViewParams.setMargins(2, 0 , 2, 0)

        val parentLayout = LinearLayout(context as MainActivity)
        parentLayout.layoutParams = parentLayoutParams
        parentLayout.orientation = LinearLayout.HORIZONTAL

        val imgView1 = ImageView(context as MainActivity)
        val imgView2 = ImageView(context as MainActivity)
        val imgView3 = ImageView(context as MainActivity)

        Picasso.get().load(recentModel.image1).resize(imgWidth, imgHeight).into(imgView1)
        Picasso.get().load(recentModel.image2).resize(imgWidth, imgHeight).into(imgView2)
        Picasso.get().load(recentModel.image3).resize(imgWidth, imgHeight).into(imgView3)

        imgView1.layoutParams = commonImgView1Params
        imgView2.layoutParams = marginImgViewParams
        imgView3.layoutParams = commonImgView2Params

        parentLayout.addView(imgView1)
        parentLayout.addView(imgView2)
        parentLayout.addView(imgView3)

        return parentLayout
    }

    fun createType2Layout(width: Int, recentModel: RecentPickModel): LinearLayout {

        var smallImgWidth = (width - 4 ) / 3
        var smallImgHeight = smallImgWidth

        var bigImgWidth = (smallImgWidth * 2) + 2
        var bigImgHeight = bigImgWidth

        // 전체 parent Layout
        var parentLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        parentLayoutParams.setMargins(0, 0, 0, 2)

        //Small Images parent Layout
        var smallParentLayoutParams = LinearLayout.LayoutParams(
                smallImgWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )

        //Big Image
        var bigViewParams = LinearLayout.LayoutParams(bigImgWidth, bigImgHeight)
        bigViewParams.setMargins(0,0, 2, 0)

        //Small Images
        var smallView1Params = LinearLayout.LayoutParams(smallImgWidth, smallImgHeight)
        smallView1Params.setMargins(0,0,0, 2)
        var smallView2Params = LinearLayout.LayoutParams(smallImgWidth, smallImgHeight)

        val parentLayout = LinearLayout(context as MainActivity)
        parentLayout.layoutParams = parentLayoutParams
        parentLayout.orientation = LinearLayout.HORIZONTAL

        val smallParentLayout = LinearLayout(context as MainActivity)
        smallParentLayout.layoutParams = smallParentLayoutParams
        smallParentLayout.orientation = LinearLayout.VERTICAL

        val bigImgView = ImageView(context as MainActivity)
        bigImgView.layoutParams = bigViewParams

        val smallImgView1 = ImageView(context as MainActivity)
        smallImgView1.layoutParams = smallView1Params

        val smallImgView2 = ImageView(context as MainActivity)
        smallImgView2.layoutParams = smallView2Params

        Picasso.get().load(recentModel.image1).resize(bigImgWidth, bigImgHeight).into(bigImgView)
        Picasso.get().load(recentModel.image2).resize(smallImgWidth, smallImgHeight).into(smallImgView1)
        Picasso.get().load(recentModel.image3).resize(smallImgWidth, smallImgHeight).into(smallImgView2)

        smallParentLayout.addView(smallImgView1)
        smallParentLayout.addView(smallImgView2)
        parentLayout.addView(bigImgView)
        parentLayout.addView(smallParentLayout)

        return parentLayout

    }

    fun createType3Layout(width: Int, recentModel: RecentPickModel): LinearLayout {

        var smallImgWidth = (width - 4 ) / 3
        var smallImgHeight = smallImgWidth

        var bigImgWidth = (smallImgWidth * 2) + 2
        var bigImgHeight = bigImgWidth

        // 전체 parent Layout
        var parentLayoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        parentLayoutParams.setMargins(0, 0, 0, 2)

        //Small Images parent Layout
        var smallParentLayoutParams = LinearLayout.LayoutParams(
                smallImgWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )

        //Big Image
        var bigViewParams = LinearLayout.LayoutParams(bigImgWidth, bigImgHeight)
        bigViewParams.setMargins(2,0, 0, 0)

        //Small Images
        var smallView1Params = LinearLayout.LayoutParams(smallImgWidth, smallImgHeight)
        smallView1Params.setMargins(0,0,0, 2)
        var smallView2Params = LinearLayout.LayoutParams(smallImgWidth, smallImgHeight)

        val parentLayout = LinearLayout(context as MainActivity)
        parentLayout.layoutParams = parentLayoutParams
        parentLayout.orientation = LinearLayout.HORIZONTAL

        val smallParentLayout = LinearLayout(context as MainActivity)
        smallParentLayout.layoutParams = smallParentLayoutParams
        smallParentLayout.orientation = LinearLayout.VERTICAL

        val bigImgView = ImageView(context as MainActivity)
        bigImgView.layoutParams = bigViewParams

        val smallImgView1 = ImageView(context as MainActivity)
        smallImgView1.layoutParams = smallView1Params

        val smallImgView2 = ImageView(context as MainActivity)
        smallImgView2.layoutParams = smallView2Params

        Picasso.get().load(recentModel.image1).resize(bigImgWidth, bigImgHeight).into(smallImgView1)
        Picasso.get().load(recentModel.image2).resize(smallImgWidth, smallImgHeight).into(smallImgView2)
        Picasso.get().load(recentModel.image3).resize(smallImgWidth, smallImgHeight).into(bigImgView)

        smallParentLayout.addView(smallImgView1)
        smallParentLayout.addView(smallImgView2)
        parentLayout.addView(smallParentLayout)
        parentLayout.addView(bigImgView)
        return parentLayout

    }


    fun changeDP(value : Int) : Int {
        var displayMetrics = resources.displayMetrics
        var dp = Math.round(value * displayMetrics.density)
        return dp
    }

    fun View.setHeight(value: Int) {
        val lp = layoutParams
        lp?.let {
            lp.height = value
            layoutParams = lp
        }
    }

    fun View.setWidth(value: Int) {
        val lp = layoutParams
        lp?.let {
            lp.width = value
            layoutParams = lp
        }
    }

}
