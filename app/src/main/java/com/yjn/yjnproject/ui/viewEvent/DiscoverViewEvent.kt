package com.yjn.yjnproject.ui.viewEvent

sealed class DiscoverViewEvent {
    object InitWAndroidEvent : DiscoverViewEvent()
    object MoreWAndroidEvent: DiscoverViewEvent()
}