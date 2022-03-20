package com.yjn.yjnproject.data.net.api

import com.hjq.http.config.IRequestApi

class ArticleListApi : IRequestApi {
    var pageSize = 0

    override fun getApi(): String {
        return "article/list/$pageSize/json"
    }
}