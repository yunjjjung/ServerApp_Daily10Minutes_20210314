package com.tjoeun.serverapp_daily10minutes_20210314.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tjoeun.serverapp_daily10minutes_20210314.R
import com.tjoeun.serverapp_daily10minutes_20210314.ViewPhotoActivity
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Proof
import com.tjoeun.serverapp_daily10minutes_20210314.datas.User
import java.text.SimpleDateFormat

class ProofAdapter(
    val mContext : Context,
    val resId : Int,
    val mList : ArrayList<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inflater.inflate(R.layout.proof_list_item, null)
        }

        val row = tempRow!!

        val data = mList[position]

        val writerProfileImg = row.findViewById<ImageView>(R.id.writerProfileImg)
        val writerNicknameTxt = row.findViewById<TextView>(R.id.writerNicknameTxt)
        val proofDateTimeTxt = row.findViewById<TextView>(R.id.proofDateTimeTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)

        contentTxt.text = data.content

        Glide.with(mContext).load(data.writer.profileImgUrls[0]).into(writerProfileImg)
        writerNicknameTxt.text = data.writer.nickName

        val sdf = SimpleDateFormat("yyyy-MM-dd\na H:mm")
        proofDateTimeTxt.text = sdf.format(data.proofDateTime.time)

        return row
    }

}