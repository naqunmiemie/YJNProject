package com.yjn.yjnproject

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.yjn.common.Common
import com.yjn.yjnproject.data.db.MyDatabase

class App : Application(), ViewModelStoreOwner{
    companion object{
        private lateinit var mInstance : App
        private lateinit var database : MyDatabase
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
        database = MyDatabase.getInstance(this)
    }

    override fun getViewModelStore(): ViewModelStore {
        TODO("Not yet implemented")
    }
}