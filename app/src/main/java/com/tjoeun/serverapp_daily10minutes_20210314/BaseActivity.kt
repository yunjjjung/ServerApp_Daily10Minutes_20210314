package com.tjoeun.serverapp_daily10minutes_20210314

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    val mContext = this

    abstract fun setupEvents()
    abstract fun setValues()

//    이 클래스는 다른 모든 액티비티의 부모로 동작.
//    이 클래스에 적힌 변수/함수는 다른 모든 액티비티가 물려받게 된다.
//    모든 화면에 공통적인 기능을 만들때는 여기서 처리하자.

//    액션바를 직접 그리기 위한 코드가 담긴 함수

    fun setCustomActionBar() {

//        1. 액션바가 어떤 모양으로 보이게 하고싶은지?
//         => 모양 : layout - xml 작성하자.

//        2. 기본 액션바를 불러내서 => 커스텀 액션바로 교체 작업.

//        기존에 달려있는 기본 액션바 불러내기. (무조건 있다고 전제)
        val defaultActionBar = supportActionBar!!

//        기존 액션바의 모드 변경 => 커스텀 액션바 모드로 변경.
        defaultActionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

//        실제로 보여줄 커스텀 화면 지정. (my_custom_action_bar)
        defaultActionBar.setCustomView(R.layout.my_custom_action_bar)

    }

}