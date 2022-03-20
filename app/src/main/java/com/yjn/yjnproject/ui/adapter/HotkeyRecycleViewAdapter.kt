package com.yjn.yjnproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yjn.yjnproject.R
import com.yjn.yjnproject.data.entity.Hotkey


class HotkeyRecycleViewAdapter : RecyclerView.Adapter<HotkeyRecycleViewAdapter.ViewHolder>() {
    private var dataList: List<Hotkey.Data> = ArrayList()
    private lateinit var itemClickListener: ItemClickListener

    fun setOnItemClickListener(listener: ItemClickListener) {
        this.itemClickListener = listener
    }

    fun setDataList(dataList: List<Hotkey.Data>?){
        if (dataList != null){
            this.dataList = dataList
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_hotkey)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hotkey, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = dataList[position].name
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(dataList[position],position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

interface ItemClickListener {
    fun onItemClick(data: Hotkey.Data, int: Int)
}

