package com.tjoeun.serverapp_daily10minutes_20210314.utils

import android.content.Context

class ContextUtil {

    companion object {

//        메모장 이름에 대응되는 개념. (환경설정 파일 이름)
        val prefName = "Daily10MinutePref"

//        저장할 데이터 항목명 변수들.
        val IS_AUTO_LOGIN = "IS_AUTO_LOGIN"

//        자동로그인 여부 저장(set) / 확인(get) 2가지 기능.

        fun setAutoLogin(context : Context, autoLogin : Boolean) {

//            메모장 파일을 열어주자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            열린 파일에 -> 자동로그인 항목을 저장하자.
            pref.edit().putBoolean(IS_AUTO_LOGIN, autoLogin).apply()

        }

//        자동로그인 저장값 확인 기능.

        fun getAutoLogin(context: Context) : Boolean {

//            메모장 파일을 열어보자.
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            자동로그인 항목에 저장된 값을 결과로 리턴.
            return pref.getBoolean(IS_AUTO_LOGIN, false)

        }

    }

}