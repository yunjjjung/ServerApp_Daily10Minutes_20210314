package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project

class EditProofActivity : BaseActivity() {

    lateinit var mProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_proof)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {



    }

    override fun setValues() {

        mProject = intent.getSerializableExtra("project") as Project

    }


}