package com.tjoeun.serverapp_daily10minutes_20210314.datas

import org.json.JSONObject

class User {

    var id = 0
    var email = ""
    var nickName = ""

    val profileImgUrls = ArrayList<String>()

    companion object {

        fun getUserDataFromJson(jsonObj : JSONObject) : User {

            val userData = User()

//            userData의 변수들에 값 대입

            return userData

        }

    }

}