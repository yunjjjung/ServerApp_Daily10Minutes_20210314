package com.tjoeun.serverapp_daily10minutes_20210314.datas

import org.json.JSONObject

class Proof {

    var id = 0
    var content = ""

//    인증글의 하위정보로 > 누가 작성했는지 User를 담아두자.
    lateinit var writer : User

    companion object {

        fun getProofFromJson(jsonObj : JSONObject) : Proof {

            val proof = Proof()

            proof.id = jsonObj.getInt("id")
            proof.content = jsonObj.getString("content")

//            게시글의 작성자?
//            User 형태 => 적당한 JSON을 넣으면 => User로 바꿔주는 기능.
//            만들어둔걸 활용하자.!

//            user 정보를 갖고있는 JSON 추출
            val userObj = jsonObj.getJSONObject("user")

            proof.writer = User.getUserDataFromJson(userObj)

            return proof
        }

    }

}