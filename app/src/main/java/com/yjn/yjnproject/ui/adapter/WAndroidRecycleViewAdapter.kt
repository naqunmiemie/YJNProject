package com.yjn.yjnproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yjn.yjnproject.R
import com.yjn.yjnproject.data.entity.WAndroid
import com.yjn.yjnproject.ui.adapter.comparator.WAndroidUserComparator

class WAndroidRecycleViewAdapter : PagingDataAdapter<WAndroid.WAndroidUser, WAndroidRecycleViewAdapter.ViewHolder>(WAndroidUserComparator) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = getItem(position)?.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_w_android,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvName: TextView = view.findViewById(R.id.tv_name)
    }


}