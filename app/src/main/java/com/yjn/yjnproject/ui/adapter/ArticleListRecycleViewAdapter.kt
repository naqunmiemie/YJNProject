package com.yjn.yjnproject.ui.adapter

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.TimeUtils
import com.yjn.yjnproject.R
import com.yjn.yjnproject.data.entity.ArticleList
import com.yjn.yjnproject.ui.activity.WebViewActivity
import com.yjn.yjnproject.ui.adapter.comparator.ArticleListComparator

class ArticleListRecycleViewAdapter : PagingDataAdapter<ArticleList.DataX, ArticleListRecycleViewAdapter.ViewHolder>(ArticleListComparator) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = Html.fromHtml(getItem(position)?.title, Html.FROM_HTML_MODE_COMPACT)
        holder.tvShareUser.text = getItem(position)?.shareUser
        holder.tvPublishTime.text = getItem(position)?.publishTime?.let {
            TimeUtils.getFriendlyTimeSpanByNow(it)
        }
        holder.tvSuperChapterName.text = "${getItem(position)?.chapterName}·${getItem(position)?.superChapterName}"
        holder.itemView.setOnClickListener {

            ActivityUtils.startActivity(
                Intent(holder.itemView.context,
                    WebViewActivity::class.java).apply {
                putExtra(WebViewActivity.url,getItem(position)?.link)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_list,parent,false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvShareUser: TextView = itemView.findViewById(R.id.tv_share_user)
        var tvPublishTime: TextView = itemView.findViewById(R.id.tv_publish_time)
        var tvSuperChapterName: TextView = itemView.findViewById(R.id.tv_super_chapter_name)    }


}