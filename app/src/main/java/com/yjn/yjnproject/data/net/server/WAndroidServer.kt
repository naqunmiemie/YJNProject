package com.yjn.yjnproject.data.net.server

import com.hjq.http.config.IRequestServer

class WAndroidServer : IRequestServer {
    override fun getHost(): String {
        return "https://www.wanandroid.com/"
    }
}