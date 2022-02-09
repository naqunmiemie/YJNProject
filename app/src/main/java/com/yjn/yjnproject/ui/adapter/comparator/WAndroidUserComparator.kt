package com.yjn.yjnproject.ui.adapter.comparator

import androidx.recyclerview.widget.DiffUtil
import com.yjn.yjnproject.data.entity.WAndroid

object WAndroidUserComparator : DiffUtil.ItemCallback<WAndroid.WAndroidUser>() {
    override fun areItemsTheSame(
        oldItem: WAndroid.WAndroidUser,
        newItem: WAndroid.WAndroidUser
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: WAndroid.WAndroidUser,
        newItem: WAndroid.WAndroidUser
    ): Boolean {
        return oldItem == newItem
    }
}