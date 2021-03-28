package com.tjoeun.serverapp_daily10minutes_20210314.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tjoeun.serverapp_daily10minutes_20210314.fragments.PhotoFragment

class PhotoViewPagerAdapter(
    fm : FragmentManager,
    val mPhotoUrlList : ArrayList<String> ) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
//        목록으로 들어오는 사진의 장수만큼 페이지를 그려달라.
        return mPhotoUrlList.size
    }

    override fun getItem(position: Int): Fragment {
//        프래그먼트 자체는 무조건 PhotoFragment가 나가도록.
//        나가기 전에, 어떤 사진을 보여줘야하는지 변수에 담고 나가도록.

        val pf = PhotoFragment()
        pf.photoURL = mPhotoUrlList[position]

        return pf
    }


}