package com.yjn.yjnproject.ui.viewEvent

import com.yjn.yjnproject.ui.base.BaseViewEvent

sealed class HomeViewEvent: BaseViewEvent() {
    object InitUserEvent : HomeViewEvent()
    data class GetUserEvent(val inputUserName: String) : HomeViewEvent()
    object RefreshUserEvent: HomeViewEvent()
}
