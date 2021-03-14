package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

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



        }

    }

    override fun setValues() {

    }

}