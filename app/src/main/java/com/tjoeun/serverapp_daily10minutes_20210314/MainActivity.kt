package com.tjoeun.serverapp_daily10minutes_20210314

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.tjoeun.serverapp_daily10minutes_20210314.adapters.ProjectAdapter
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ContextUtil
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    val mProjectList = ArrayList<Project>()

    lateinit var mAdapter : ProjectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
        setCustomActionBar()
    }

    override fun setupEvents() {

//        프로젝트 목록을 누르면 => 상세화면으로 이동
        projectListView.setOnItemClickListener { parent, view, position, id ->

//            어떤 프로젝트가 클릭되었나?

            val clickedProject = mProjectList[position]

//            프로젝트 정보를 통째로 들고 => 상세화면으로 이동

            val myIntent = Intent(mContext, ViewProjectDetailActivity::class.java)
            myIntent.putExtra("projectInfo", clickedProject)
            startActivity(myIntent)

        }

        logoutBtn.setOnClickListener {

//          [연습문제]  정말 로그아웃 하시겠습니까? 얼럿 띄우고 => OK 인 경우에만 로그아웃.

            val alert = AlertDialog.Builder(mContext)
            alert.setTitle("로그아웃 확인")
            alert.setMessage("정말 로그아웃 하시겠습니까?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->

//                확인 눌리면 => 로그아웃 실제 처리
//            로그인?  id / pw => 서버에 회원이 맞나? 요청. => 성공시 토큰을 기기에 저장.

//            로그아웃? 기기에 저장된 토큰을 날리는 작업. => 저장된 토큰을 빈칸으로 돌리자.

                ContextUtil.setToken(mContext, "")

//            다시 로그인 화면으로 이동.

                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)

                finish()

            })
            alert.setNegativeButton("취소", null)

            alert.show()



        }

    }

    override fun setValues() {

        mAdapter = ProjectAdapter(mContext, R.layout.project_list_item, mProjectList)

        projectListView.adapter = mAdapter

//        메인화면에 들어오면 => 프로젝트 목록이 뭐뭐있는지 서버에 요청 (ServerUtil 함수 추가)
//        받아온 결과를 분석해서 => Project() 형태로 만들어서 => mProjectList에 add해주자.

        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler {

            override fun onResponse(json: JSONObject) {

                val dataObj = json.getJSONObject("data")

//                data 내부의 [  ] 배열을 가져오는 코드.
                val projectsArr = dataObj.getJSONArray("projects")

//                projects [  ]  => 이름표가 아니라, 순서대로 하나씩 추출.
//                첫번째 [0] ~ 마지막 [갯수직전] 까지 반복적으로 하나씩 추출. => Kotlin 반복문 활용.

                for (i  in   0 until projectsArr.length()) {

//                    프로젝트 정보를 0~끝까지 projectsArr에서 꺼내서 파싱.

//                    {  } 프로젝트 정보 덩어리 JSONObject 추출
                    val projectObj = projectsArr.getJSONObject(i)


//                    projectObj 를 가지고 => Project()로 변환 기능 사용.
                    val project = Project.getProjectDataFromJson(projectObj)


//                    가공된 데이터를 목록에 추가.
                    mProjectList.add(project)

                }

//                for문 끝나면 => 모든 프로젝트가 목록에 추가된 상태.
//                목록 변경이 생겼으니 => 어댑터 새로고침 (UI 변경) 필요

                runOnUiThread {
                    mAdapter.notifyDataSetChanged()
                }


            }

        } )


    }

}