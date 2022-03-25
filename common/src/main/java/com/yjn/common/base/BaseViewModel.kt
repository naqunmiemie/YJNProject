package com.yjn.common.base

import android.annotation.SuppressLint
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(), LifecycleOwner {
    @SuppressLint("StaticFieldLeak")
    private val mLifecycle: LifecycleRegistry = LifecycleRegistry(this)

    init{
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onCleared() {
        super.onCleared()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    override fun getLifecycle(): Lifecycle {
        return mLifecycle
    }

}