package com.yjn.yjnproject.ui.viewState

import com.yjn.yjnproject.data.entity.ArticleList
import com.yjn.yjnproject.data.entity.Hotkey

data class HomeViewState(
    var hotkey: Hotkey? = null,
    var articleList: ArticleList? = null
)