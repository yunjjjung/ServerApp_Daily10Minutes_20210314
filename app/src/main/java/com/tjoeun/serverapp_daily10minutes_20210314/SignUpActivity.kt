package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        emailEdt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

//                Log.d("바뀐내용", s.toString())

//                이메일의 내용이 타이핑되었다 => 재검사 요구 문장으로 변경.

                checkResultTxt.text = "이메일 중복 확인을 해주세요."

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        checkEmailBtn.setOnClickListener {

//            입력한 이메일 => 서버에 중복여부 확인.

            val inputEmail = emailEdt.text.toString()

//            서버에 -> /email_check 로 중복확인 요청. -> ServerUtil에 함수 추가 필요.

            ServerUtil.getRequestEmailCheck(inputEmail, object : ServerUtil.JsonResponseHandler {

                override fun onResponse(json: JSONObject) {

//                    코드값이 200 -> 사용가능. 아니면 사용 불가.

                    val code = json.getInt("code")

                    runOnUiThread {

                        if (code == 200) {
                            checkResultTxt.text = "사용해도 좋은 이메일입니다."
                        }
                        else {
                            checkResultTxt.text = "다른 이메일을 입력 후, 재검사 해주세요."
                        }

                    }


                }

            } )

        }

        signUpBtn.setOnClickListener {

//            이메일 / 비번 / 닉네임 -> 서버 회원가입기능에 전송.

            val email = emailEdt.text.toString()
            val pw = pwEdt.text.toString()
            val nick = nicknameEdt.text.toString()

//            서버 - 회원가입기능에 전송 : ServerUtil에 회원가입 함수 필요

            ServerUtil.putRequestSignUp(email, pw, nick, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(json: JSONObject) {

//                    회원가입 결과에 따른 UI 반영 필요.
//                    code : 200이면 가입성공 -> 토스트 + 회원가입 화면 종료.
//                    그외 값 : 서버가 주는 실패 사유를 -> 토스트

                    val code = json.getInt("code")

                    runOnUiThread {

                        if (code == 200) {

//                            가입한 사람의 이름을 추출해서, 환영 메세지
//                            json > data { } > user { } > "nick_name" String 추출.

                            val dataObj = json.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")
                            val userName = userObj.getString("nick_name")

                            Toast.makeText(mContext, "${userName}님 환영합니다.", Toast.LENGTH_SHORT).show()

                            finish()
                        } else {

//                            서버가 주는 message 스트링을 토스트로 출력.
                            val message = json.getString("message")

                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                        }

                    }


                }

            })

        }

    }

    override fun setValues() {

    }

}