package com.yjn.common.base

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.yjn.common.util.L

abstract class BaseApplication : Application(), ViewModelStoreOwner {
    companion object{
        private lateinit var mInstance : BaseApplication
        fun getInstance(): BaseApplication{
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
            initMainProcess()
        }
    }

    abstract fun initMainProcess()

    override fun getViewModelStore(): ViewModelStore {
        TODO("Not yet implemented")
    }
}