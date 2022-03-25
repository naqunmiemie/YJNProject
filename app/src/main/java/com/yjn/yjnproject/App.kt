package com.yjn.yjnproject

import com.hjq.http.EasyConfig
import com.hjq.http.config.IRequestInterceptor
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams
import com.hjq.http.request.HttpRequest
import com.tencent.mmkv.MMKV
import com.yjn.common.Common
import com.yjn.common.base.BaseApplication
import com.yjn.yjnproject.data.db.AppDatabase
import com.yjn.yjnproject.data.net.model.RequestHandler
import com.yjn.yjnproject.data.net.server.WAndroidServer
import okhttp3.OkHttpClient

class App : BaseApplication() {
    companion object{
        private lateinit var database : AppDatabase
    }



    override fun initMainProcess() {
        Common.init(this)
        database = AppDatabase.getInstance(this)

        MMKV.initialize(this)

        val okHttpClient = OkHttpClient.Builder().build()

        EasyConfig.with(okHttpClient) // 是否打印日志
            //.setLogEnabled(BuildConfig.DEBUG)
            // 设置服务器配置
            .setServer(WAndroidServer()) // 设置请求处理策略
            .setHandler(RequestHandler(this)) // 设置请求参数拦截器
            .setInterceptor(object : IRequestInterceptor {
                override fun interceptArguments(
                    httpRequest: HttpRequest<*>?,
                    params: HttpParams,
                    headers: HttpHeaders
                ) {
                    headers.put("timestamp", System.currentTimeMillis().toString())
                }
            }) // 设置请求重试次数
            .setRetryCount(1) // 设置请求重试时间
            .setRetryTime(2000) // 添加全局请求参数
            .into()
    }
}