package com.yjn.yjnproject.data.net.api

import com.hjq.http.config.IRequestApi

class HotkeyApi : IRequestApi {
    override fun getApi(): String {
        return "hotkey/json"
    }
}