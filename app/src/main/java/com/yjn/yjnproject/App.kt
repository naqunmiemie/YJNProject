package com.yjn.yjnproject

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.hjq.http.EasyConfig
import com.hjq.http.config.IRequestInterceptor
import com.hjq.http.model.HttpHeaders
import com.hjq.http.model.HttpParams
import com.hjq.http.request.HttpRequest
import com.tencent.mmkv.MMKV
import com.yjn.common.Common
import com.yjn.common.util.L
import com.yjn.yjnproject.data.db.AppDatabase
import com.yjn.yjnproject.data.net.model.RequestHandler
import com.yjn.yjnproject.data.net.server.WAndroidServer
import okhttp3.OkHttpClient

class App : Application(), ViewModelStoreOwner{
    companion object{
        private lateinit var mInstance : App
        private lateinit var database : AppDatabase
        fun getInstance(): App{
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        L.i("App onCreate")
        mInstance = this
        //只有主进程，才执行后续逻辑
        if(packageName.equals(getProcessName())) {
            L.i("main process init")
            init()
        }
    }


    private fun init() {
        Common.init(mInstance)
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
            //                .addParam("token", "yjn")
            .into()

    }

    override fun getViewModelStore(): ViewModelStore {
        TODO("Not yet implemented")
    }
}