package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {

//            입력한 아이디 / 비번을 변수로 저장
            val inputId = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            이 아이디/비번이 회원이 맞는지, 서버에 확인 요청. => 로그인 요청.

            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {
//                    JSON 파싱 -> UI 반영 코드 작성 영역
                }

            })

        }

    }

    override fun setValues() {

    }

}