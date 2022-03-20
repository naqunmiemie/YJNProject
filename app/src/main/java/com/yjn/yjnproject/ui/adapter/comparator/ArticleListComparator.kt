package com.yjn.yjnproject.ui.adapter.comparator

import androidx.recyclerview.widget.DiffUtil
import com.yjn.yjnproject.data.entity.ArticleList

object ArticleListComparator : DiffUtil.ItemCallback<ArticleList.DataX>() {
    override fun areItemsTheSame(
        oldItem: ArticleList.DataX,
        newItem: ArticleList.DataX
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ArticleList.DataX,
        newItem: ArticleList.DataX
    ): Boolean {
        return oldItem == newItem
    }
}