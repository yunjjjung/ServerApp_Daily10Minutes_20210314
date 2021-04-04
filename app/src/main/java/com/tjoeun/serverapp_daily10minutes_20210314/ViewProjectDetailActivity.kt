package com.tjoeun.serverapp_daily10minutes_20210314

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_project_detail.*
import org.json.JSONObject

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

//        인증글 보러가기 눌리면 => 화면 이동

        viewProofBtn.setOnClickListener {

            val myIntent = Intent(mContext, ViewProofActivity::class.java)
            myIntent.putExtra("project", mProject)
            startActivity(myIntent)

        }

//        참가신청 버튼이 눌리면 => 신청 API 호출.

        applyBtn.setOnClickListener {

            ServerUtil.postRequestApplyProject(
                mContext,
                mProject.id,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(json: JSONObject) {

//                    신청 결과에 따른 처리.
//                    code : 200 => 신청 성공, 그외 값 : 신청 실패. message : 실패사유String
//                    400 - 잘못된 데이터 유입.
//                    403 - 권한이 없는데 요청. ex. 관리자 전용 기능 => 일반회원이 요청.
//                    404 - /user, /project 등 기능 주소를 잘못 입력한 경우. (Not found)
//                    500 - 서버 내부의 로직 에러.

                        val code = json.getInt("code")

                        if (code == 200) {
//                        정상 신청 완료 => 서버가 최신 상태값 다시 내려줌. => 다시 파싱해서 UI 반영.

//                            신청시 처리방안
//                            1) 참여 인원수 재확인 (서버에서 다시 확인)
//                            2) 신청하기 버튼 대신, 포기하기 버튼으로 대체.

                            val dataObj = json.getJSONObject("data")
                            val projectObj = dataObj.getJSONObject("project")

//                            projectObj 하나의 프로젝트 정보를 담고있는 JSONObject.
//                            Project 클래스의 파싱 기능에 집어넣기 적당함.

                            val projectData = Project.getProjectDataFromJson(projectObj)

//                            화면에 뿌려지는 프로젝트 : mProject 갱신
                            mProject = projectData

//                            UI상에서도 문구 반영
                            runOnUiThread {
                                refreshUI()
                            }


                        } else {
//                        여타 이유로 실패.
                            val message = json.getString("message")

                            runOnUiThread {
                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                            }

                        }


                    }

                })

        }

//        중도포기 버튼 눌리면 => 포기 API 호출 => UI 새로고침

        giveUpBtn.setOnClickListener {

            ServerUtil.deleteRequestGiveUpProject(
                mContext,
                mProject.id,
                object : ServerUtil.JsonResponseHandler {

                    override fun onResponse(json: JSONObject) {

                        val code = json.getInt("code")

                        if (code == 200) {

//                            참여 신청 API : 어떤 변경사항이 생겼는지 알 수 있도록, 프로젝트 데이터 내려줌.
//                            포기 API : 프로젝트 데이터 X. => 프로젝트 상태를 새로 조회해서 반영해야함.

                            ServerUtil.getRequestProjectDetail(mContext, mProject.id, object : ServerUtil.JsonResponseHandler {
                                override fun onResponse(json: JSONObject) {

                                    val dataObj = json.getJSONObject("data")
                                    val projectObj = dataObj.getJSONObject("project")

                                    val projectData = Project.getProjectDataFromJson(projectObj)

                                    mProject = projectData

                                    runOnUiThread {
                                        refreshUI()
                                    }

                                }
                            })

                        } else {
                            val message = json.getString("message")
                            runOnUiThread {

                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                            }
                        }

                    }

                })

        }

//        참여인원 보기 누르면 => 프로젝트 정보를 들고 => 멤버리스트화면으로 이동

        viewMemberBtn.setOnClickListener {

            val myIntent = Intent(mContext, ViewMemberListActivity::class.java)
            myIntent.putExtra("project", mProject)
            startActivity(myIntent)

        }

    }

    override fun setValues() {

//        들어오는 intent를 통해서 프로젝트 정보 저장.
        mProject = intent.getSerializableExtra("projectInfo") as Project

        refreshUI()

    }

//    서버에서 받은 데이터 (mProject) 를 기반으로 => UI 새로 반영 함수.

    fun refreshUI() {
//        프로젝트 제목 / 이미지 표시.
        projectTitleTxt.text = mProject.title
        Glide.with(mContext).load(mProject.imageURL).into(projectImg)

//        프로젝트 설명 문구 반영
        projectDescTxt.text = mProject.description

//        참여 인원 수 반영 : ?명 양식으로 가공
        memberCountTxt.text = "${mProject.ongoingUsersCount}명"

//        인증 방법 : 단순 text 반영.
        proofMethodTxt.text = mProject.proofMethod

//        내가 이 프로젝트에 참여중인가?
//        참가상태 == "ONGOING"  이면 참여중, 그 외 모든 경우 참여 X.

        if (mProject.myLastStatus == "ONGOING") {

//            참여하기 버튼 숨김.
//            포기하기 버튼 보여주기.

            applyBtn.visibility = View.GONE
            giveUpBtn.visibility = View.VISIBLE

        }
        else {

//            참여하기 보여주고, 포기하기 숨겨주고.
            applyBtn.visibility = View.VISIBLE
            giveUpBtn.visibility = View.GONE

        }

//        프로젝트 데이터의 해쉬태그 갯수에 따른 UI 처리
        
//        갯수 : 0개 - tagsLayout 숨김. 0개 아니면 - tagsLayout 보여주기 + 필요한만큼 텍스트뷰 추가

        if (mProject.hashTags.size == 0) {
            tagsLayout.visibility = View.GONE
        }
        else {
            tagsLayout.visibility = View.VISIBLE

//            기존에 태그들이 달려있다면, 전부 삭제하고 나서 => 새로 태그를 추가.
            tagsLayout.removeAllViews()

//            태그의 갯수 만큼, 텍스트뷰를 (tagsLayout에) 추가.

            for (tag  in mProject.hashTags) {

//                xml에 그려두지 않은, 텍스트뷰를 코틀린에서 추가하자.
//                1) res-layout-xml을 별도로 추가. => LayoutInflater 활용
//                2) TextView 변수 자체를 생성. (이번에 새로 활용)

                val tagTextView = TextView(mContext)
                tagTextView.text = "#${tag}"

//                텍스트컬러 지정 => #5555FF COLOR
                tagTextView.setTextColor(Color.parseColor("#5555FF"))

//                만들어낸 텍스트뷰를 -> tagsLayout에 추가.

                tagsLayout.addView(tagTextView)

            }
        }

    }

}