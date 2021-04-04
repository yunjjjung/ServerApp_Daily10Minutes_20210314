package com.tjoeun.serverapp_daily10minutes_20210314.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tjoeun.serverapp_daily10minutes_20210314.R
import com.tjoeun.serverapp_daily10minutes_20210314.ViewPhotoActivity
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Project
import com.tjoeun.serverapp_daily10minutes_20210314.datas.Proof
import com.tjoeun.serverapp_daily10minutes_20210314.datas.User

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


        return row
    }

}