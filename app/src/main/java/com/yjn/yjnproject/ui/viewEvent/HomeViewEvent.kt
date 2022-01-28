package com.yjn.yjnproject.ui.viewEvent

import com.yjn.yjnproject.ui.base.BaseViewEvent

sealed class HomeViewEvent: BaseViewEvent() {
    object InitUserEvent : HomeViewEvent()
    object GetUserEvent: HomeViewEvent()
    object RefreshUserEvent: HomeViewEvent()
}
