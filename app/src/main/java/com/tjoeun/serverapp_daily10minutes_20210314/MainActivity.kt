package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project

class MainActivity : BaseActivity() {

    val mProjectList = ArrayList<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        메인화면에 들어오면 => 프로젝트 목록이 뭐뭐있는지 서버에 요청 (ServerUtil 함수 추가)
//        받아온 결과를 분석해서 => Project() 형태로 만들어서 => mProjectList에 add해주자.




    }

}