package com.tjoeun.serverapp_daily10minutes_20210314

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ContextUtil
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        자동로그인 체크 이벤트 -> true : 로그인 성공시 자동 로그인 됩니다. 토스트 / false : 자동로그인 해제.

        autoLoginCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->

//            isChecked : 지금 변경된 체크 여부.

//            체크 된 값을 SharedPreferences에 기록, 앱이 다시 켜질때 => 체크 여부 반영.

            ContextUtil.setAutoLogin(mContext, isChecked)

            if (isChecked) {
//                지금 체크로 찍혔다.
                Toast.makeText(mContext, "로그인 성공시 자동 로그인 됩니다.", Toast.LENGTH_SHORT).show()
            }
            else {
//                지금 체크를 해제했다.
                Toast.makeText(mContext, "자동 로그인이 해제됩니다.", Toast.LENGTH_SHORT).show()
            }

        }

        signUpBtn.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }

        loginBtn.setOnClickListener {

//            입력한 아이디 / 비번을 변수로 저장
            val inputId = emailEdt.text.toString()
            val inputPw = pwEdt.text.toString()

//            이 아이디/비번이 회원이 맞는지, 서버에 확인 요청. => 로그인 요청.

            ServerUtil.postRequestLogin(inputId, inputPw, object : ServerUtil.JsonResponseHandler {

                override fun onResponse(json: JSONObject) {
//                    JSON 파싱 -> UI 반영 코드 작성 영역

                    Log.d("화면입장", json.toString())

                    val code = json.getInt("code")

//                    서버 / 앱 약속 : code 가 200이면 로그인 성공. 그외 모든 값 로그인 실패

                    if (code == 200) {
//                        로그인 성공시 처리. => 메인화면 진입, 로그인화면 종료

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

                        finish()

                    } else {
//                        실패 처리. => 서버가 알려주는 실패사유를 토스트로 띄워보자. (UI반영)

                        val message = json.getString("message")

//                       (백그라운드 쓰레드에서) 토스트를 띄우려고 하면 앱이 죽는다.
//                        UI 동작 관련 코드는, 반드시 UI 쓰레드에서만 동작시켜야 함.

                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }

            })

        }

    }

    override fun setValues() {

//        저장된 자동로그인 여부를 불러와서 => 체크박스에 반영.

        autoLoginCheckBox.isChecked = ContextUtil.getAutoLogin(mContext)


    }

}