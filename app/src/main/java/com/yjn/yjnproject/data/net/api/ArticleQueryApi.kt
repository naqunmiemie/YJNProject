package com.yjn.yjnproject.data.net.api

import com.hjq.http.annotation.HttpRename
import com.hjq.http.config.IRequestApi

class ArticleQueryApi :IRequestApi{
    override fun getApi(): String {
        return "article/query/0/json"
    }

    /** 搜索关键字  */
    @HttpRename("k")
    var keyword: String = "搬砖不再有"

}