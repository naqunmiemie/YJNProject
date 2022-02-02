package com.yjn.yjnproject.data.net.api

import com.hjq.http.config.IRequestApi

class GetUserApi : IRequestApi {
    private var userId: String? = null
    override fun getApi(): String {
        return "users/$userId"
    }


    fun setUserId(userId: String):GetUserApi{
        this.userId = userId
        return this
    }
}