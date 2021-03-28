package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import kotlinx.android.synthetic.main.activity_view_project_detail.*

class ViewProjectDetailActivity : BaseActivity() {

//    상세화면에서 프로젝트 정보는 여러 함수가 공유해서 사용해야함.
//    그런 변수는 멤버변수로 만드는게 편함.
//    변수에 객체를 담는건 => onCreate 이후에. => lateinit var 사용하자.

    lateinit var mProject : Project


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_project_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        들어오는 intent를 통해서 프로젝트 정보 저장.
        mProject = intent.getSerializableExtra("projectInfo") as Project

//        프로젝트 제목 / 이미지 표시.
        projectTitleTxt.text = mProject.title
        Glide.with(mContext).load(mProject.imageURL).into(projectImg)

//        프로젝트 설명 문구 반영
        projectDescTxt.text = mProject.description

//        참여 인원 수 반영 : ?명 양식으로 가공
        memberCountTxt.text = "${mProject.ongoingUsersCount}명"

    }

}