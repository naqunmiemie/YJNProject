package com.yjn.yjnproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yjn.yjnproject.R
import com.yjn.yjnproject.data.entity.ArticleList
import com.yjn.yjnproject.ui.adapter.comparator.ArticleListComparator

class ArticleListRecycleViewAdapter : PagingDataAdapter<ArticleList.DataX, ArticleListRecycleViewAdapter.ViewHolder>(ArticleListComparator) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = getItem(position)?.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_list,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
    }


}