package com.yjn.yjnproject.data.net.api

import com.hjq.http.config.IRequestApi
import com.hjq.http.config.IRequestServer

class SearchAuthorApi() : IRequestApi, IRequestServer{
    override fun getApi(): String {
        return "wxarticle/chapters/json"
    }

    override fun getHost(): String {
        return "https://www.wanandroid.com/"
    }

    /** 公众号 id  */
    private var id = 0

    fun setId(id: Int): SearchAuthorApi {
        this.id = id
        return this
    }
}