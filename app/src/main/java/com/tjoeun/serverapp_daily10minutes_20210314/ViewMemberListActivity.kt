package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import com.tjoeun.serverapp_daily10minutes_20210314.datas.User
import com.tjoeun.serverapp_daily10minutes_20210314.utils.ServerUtil
import org.json.JSONObject

class ViewMemberListActivity : BaseActivity() {

    lateinit var mProject : Project

    val mMemberList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_member_list)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

//        서버에 참여인원이 누구누구 있는지 요청 => 파싱 => mMemberList에 추가 (add)

        ServerUtil.getRequestProjectMembers(mContext, mProject.id, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {

            }

        })

    }

}