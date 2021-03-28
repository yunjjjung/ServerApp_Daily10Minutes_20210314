package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.tjoeun.serverapp_daily10minutes_20210314.adapters.PhotoViewPagerAdapter
import com.tjoeun.serverapp_daily10minutes_20210314.datas.User
import kotlinx.android.synthetic.main.activity_view_photo.*

class ViewPhotoActivity : BaseActivity() {

    lateinit var mUser : User

    lateinit var mPhotoAdapter : PhotoViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_photo)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        뷰페이저의 페이지 변경되었을때 => 이벤트 처리.
        profilePhotoViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {

//                페이지를 완전히 넘겼을때 실행되는 코드.
//                현재페이지 / 전체장수  => ex. 2 / 5 로 텍스트 반영.

                photoCountTxt.text = "${position+1} / ${mUser.profileImgUrls.size}"

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })


    }

    override fun setValues() {

        mUser = intent.getSerializableExtra("user") as User

        mPhotoAdapter = PhotoViewPagerAdapter(supportFragmentManager, mUser.profileImgUrls)
        profilePhotoViewPager.adapter = mPhotoAdapter

//        사진이 실제로 몇장인지 받아서 텍스트뷰에 반영.
        photoCountTxt.text = "1 / ${mUser.profileImgUrls.size}"

    }

}