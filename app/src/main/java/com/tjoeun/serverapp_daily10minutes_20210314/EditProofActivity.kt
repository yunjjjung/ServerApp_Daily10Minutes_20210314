package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_edit_proof.*
import org.json.JSONObject

class EditProofActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_proof)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        writeBtn.setOnClickListener {

//            5글자 이상은 입력하게 해보자.

            val inputContent = contentEdt.text.toString()

            if (inputContent.length < 5) {
                Toast.makeText(mContext, "최소 5글자 이상은 입력해야합니다.", Toast.LENGTH_SHORT).show()
            }
            else {

//                입력값을 서버에 인증글 작성 기능으로 전달. => ServerUtil 기능 필요.

                ServerUtil.postRequestWriteProof(
                    mContext,
                    mProject.id,
                    inputContent,
                    object : ServerUtil.JsonResponseHandler {
                        override fun onResponse(json: JSONObject) {

                            val code = json.getInt("code")

                            if (code == 200) {

//                                인증글 작성에 성공.
//                                토스트로 => 오늘의 인증을 완료했습니다.
//                                작성 화면 종료.

                                runOnUiThread {

                                    Toast.makeText(mContext, "오늘의 인증을 완료했습니다.", Toast.LENGTH_SHORT).show()
                                    finish()

                                }

                            } else {
//                            서버가 인증글 게시를 막은 상황.
//                            사유를 message로 받아서 토스트로 띄워주자.

                                val message = json.getString("message")

                                runOnUiThread {
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                                }

                            }

                        }

                    })


            }

        }

    }

    override fun setValues() {
        mProject = intent.getSerializableExtra("project") as Project
    }

}