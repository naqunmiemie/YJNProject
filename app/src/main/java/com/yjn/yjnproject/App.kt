package com.yjn.yjnproject

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.yjn.common.Common

class App : Application(), ViewModelStoreOwner{
    companion object{
        lateinit var mInstance : App
        fun getInstance(): App{
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        init()
    }


    private fun init() {
        Common.init(mInstance)
    }

    override fun getViewModelStore(): ViewModelStore {
        TODO("Not yet implemented")
    }
}