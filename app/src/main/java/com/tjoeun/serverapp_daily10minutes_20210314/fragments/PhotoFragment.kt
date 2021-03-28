package com.tjoeun.serverapp_daily10minutes_20210314.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tjoeun.serverapp_daily10minutes_20210314.R
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : Fragment() {

//    이 프래그먼트가 보여줘야할 사진이 어딨는지 URL을 멤버변수로 저장.
    var photoURL = "" // String으로 들어올거라는 암시.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        보여줘야할 사진들을 => 포토뷰에 반영.
        Glide.with(activity!!).load(photoURL).into(photoView)

    }

}