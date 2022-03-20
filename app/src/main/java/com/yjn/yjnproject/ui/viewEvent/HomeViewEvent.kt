package com.yjn.yjnproject.ui.viewEvent

sealed class HomeViewEvent {
    data class ArticleQueryEvent(val keyword: String) : HomeViewEvent()
}
