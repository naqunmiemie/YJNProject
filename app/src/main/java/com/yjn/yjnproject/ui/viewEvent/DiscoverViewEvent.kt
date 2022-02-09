package com.yjn.yjnproject.ui.viewEvent

import com.yjn.yjnproject.ui.base.BaseViewEvent

sealed class DiscoverViewEvent : BaseViewEvent() {
    object InitWAndroidEvent : DiscoverViewEvent()
    object MoreWAndroidEvent: DiscoverViewEvent()
}