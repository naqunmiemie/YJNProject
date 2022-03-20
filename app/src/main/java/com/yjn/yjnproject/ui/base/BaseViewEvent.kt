package com.yjn.yjnproject.ui.base

sealed class BaseViewEvent{
    object InitEvent : BaseViewEvent()
    object RefreshEvent: BaseViewEvent()
}
