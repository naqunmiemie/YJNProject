package com.yjn.yjnproject.data.net.server

import com.hjq.http.config.IRequestServer

class GithubServer : IRequestServer {
    override fun getHost(): String {
        return "https://api.github.com/"
    }
}