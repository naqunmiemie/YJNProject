package com.yjn.yjnproject.ui.viewModel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.yjn.yjnproject.ui.base.BaseViewModel
import com.yjn.yjnproject.ui.paging3.pagingSource.WAndroidUserPagingSource

class DiscoverViewModel : BaseViewModel() {

    fun getData() = Pager(PagingConfig(pageSize = 42)) {
        WAndroidUserPagingSource(this@DiscoverViewModel)
    }.flow
}