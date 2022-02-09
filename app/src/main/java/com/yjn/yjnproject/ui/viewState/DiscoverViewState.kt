package com.yjn.yjnproject.ui.viewState

import androidx.paging.Pager
import com.yjn.yjnproject.data.entity.WAndroid
import com.yjn.yjnproject.ui.base.BaseViewState

data class DiscoverViewState(
    val wAndroidpager: Pager<Int,WAndroid.WAndroidUser>?,
) : BaseViewState()