package com.yjn.yjnproject.ui.adapter

import android.annotation.SuppressLint
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.TimeUtils
import com.yjn.yjnproject.R
import com.yjn.yjnproject.data.entity.ArticleList

class ArticleQueryRecycleViewAdapter : RecyclerView.Adapter<ArticleQueryRecycleViewAdapter.ViewHolder>() {

    private var dataList: List<ArticleList.DataX> = ArrayList()

    fun setDataList(dataList: List<ArticleList.DataX>?){
        if (dataList != null){
            this.dataList = dataList
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvShareUser: TextView = itemView.findViewById(R.id.tv_share_user)
        var tvPublishTime: TextView = itemView.findViewById(R.id.tv_publish_time)
        var tvSuperChapterName: TextView = itemView.findViewById(R.id.tv_super_chapter_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_article_list, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = Html.fromHtml(dataList[position].title,FROM_HTML_MODE_COMPACT)
        holder.tvShareUser.text = dataList[position].shareUser
        holder.tvPublishTime.text = TimeUtils.getFriendlyTimeSpanByNow(dataList[position].publishTime)
        holder.tvSuperChapterName.text = "${dataList[position].chapterName}·${dataList[position].superChapterName}"
        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
