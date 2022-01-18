package com.yjn.yjnproject

import android.app.Application
import com.yjn.common.Common

class App : Application() {
    companion object{
        lateinit var mInstance : App
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        init()
    }

    private fun init() {
        Common.init(mInstance)
    }
}