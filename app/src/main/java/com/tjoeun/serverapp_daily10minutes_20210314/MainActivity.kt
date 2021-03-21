package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import org.json.JSONObject

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

        ServerUtil.getRequestProjectList(object : ServerUtil.JsonResponseHandler {

            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")

//                data 내부의 [  ] 배열을 가져오는 코드.
                val projectsArr = dataObj.getJSONArray("projects")

//                projects [  ]  => 이름표가 아니라, 순서대로 하나씩 추출.
//                첫번째 [0] ~ 마지막 [갯수직전] 까지 반복적으로 하나씩 추출. => Kotlin 반복문 활용.

                for (i  in   0 until projectsArr.length()) {
                    Log.d("프로젝트목록반복", "${i}번째")
                }

            }

        } )


    }

}