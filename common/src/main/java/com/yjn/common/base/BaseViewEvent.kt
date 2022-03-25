package com.yjn.common.base

sealed class BaseViewEvent{
    object InitEvent : BaseViewEvent()
    object RefreshEvent: BaseViewEvent()
}
