package com.tjoeun.serverapp_daily10minutes_20210314.utils

import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    API를 호출해주는 함수들을 모아두기 위한 클래스. (코드 정리 차원)



//    ServerUtil.함수() 처럼, 클래스이름. 만 해도 바로 사용하게 도와주는 코드.
//    JAVA - static 개념에 대응되는 코드.

    companion object {

//    호스트 주소를 편하게 입력/관리하기 한 변수.

        val HOST_URL = "http://15.164.153.174"

//        함수 작성 - 로그인 기능 담당 함수.

        fun postRequestLogin(id : String, pw : String) {

//            실제 기능 수행 주소 ex. 로그인 - http://15.164.153.174/user
//            HOST_URL/user => 최종 주소 완성.

            val urlString = "${HOST_URL}/user"

//            POST 메쏘드 - formBody에 데이터 첨부.
            val formData = FormBody.Builder()
                .add("email", id)
                .add("password", pw)
                .build()

//            API 요청(Request)을 어디로 어떻게 할건지 종합하는 변수.
            val request = Request.Builder()
                .url(urlString) // 어디로 가는지?
                .post(formData) // POST방식 - 필요데이터(formData) 들고 가도록
                .build()

//            startActivity처럼 -> 실제로 Request를 수행하는 코드.
//            클라이언트로써 동작하도록 도와주는 라이브러리 : OkHttp

            val client = OkHttpClient()

//            클라이언트가 실제 리퀘스트 수행. (newCall)
//            서버에 다녀와서, 서버가 하는 말 (응답-Response / CallBack)을 처리하는 코드 같이 작성.

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
//                    서버 연결 자체를 실패.
//                    데이터 소진, 서버가 터짐 등등의 사유로 아예 연결 실패.

//                    반대 - 로그인 비번 틀림(실패), 회원가입(이메일중복 실패) => 연결은 성공, 결과만 실패.
//                    여기서 실행되지 않는다.
                }

                override fun onResponse(call: Call, response: Response) {
//                    서버의 응답을 받아낸 경우.
//                    응답(Response) > 내부의 본문(body)만 활용. > String 형태로 저장.

//                    toString() X , string() 활용
                    val bodyString = response.body!!.string()

//                    bodyString은, 인코딩 된 상태라 읽기가 어렵다. (한글 깨짐)
//                    bodyString을 > JSONObject 으로 변환시키면 > 읽을 수 있게됨.

                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답본문", jsonObj.toString())

//                    연습 : code 에 적힌 숫자 (Int) 가 얼마인가?

                    val codeNum = jsonObj.getInt("code")

//                    연습-활용 : codeNum 200이면, 로그인 성공. 아니면 로그인 실패. 로그 찍기
//                    로그인에 실패시 => 서버에서 알려주는 실패 사유를 로그로 찍자.

                    if (codeNum == 200) {
                        Log.d("로그인결과", "성공")
                    }
                    else {
                        Log.e("로그인결과", "실패")

//                        추가로, message로 달려있는 String 을 추출.
                        val msgStr = jsonObj.getString("message")
                        Log.e("로그인실패사유", msgStr)

                    }


                }

            })

        }

    }

}







