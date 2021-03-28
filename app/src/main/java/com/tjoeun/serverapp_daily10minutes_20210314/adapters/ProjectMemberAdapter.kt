package com.tjoeun.serverapp_daily10minutes_20210314.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tjoeun.serverapp_daily10minutes_20210314.R
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import com.tjoeun.serverapp_daily10minutes_20210314.datas.User

class ProjectMemberAdapter(
    val mContext : Context,
    val resId : Int,
    val mList : ArrayList<User>) : ArrayAdapter<User>(mContext, resId, mList) {

    val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inflater.inflate(R.layout.member_list_item, null)
        }

        val row = tempRow!!

        val memberData = mList[position]

        val memberProfileImg = row.findViewById<ImageView>(R.id.memberProfileImg)
        val memberNicknameTxt = row.findViewById<TextView>(R.id.memberNicknameTxt)

        Glide.with(mContext).load(memberData.profileImgUrls[0]).into(memberProfileImg)
        memberNicknameTxt.text = memberData.nickName


        return row
    }

}