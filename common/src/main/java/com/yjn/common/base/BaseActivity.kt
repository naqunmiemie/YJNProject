package com.yjn.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    /** 根布局 */
    lateinit var binding: VB
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as VB
        setContentView(binding.root)
        initActivity()
    }

    protected open fun initActivity() {
        initView()
        initData()
    }

    /**
     * 初始化控件
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    protected open fun <T : ViewModel?> getActivityScopeViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(this,AndroidViewModelFactory(application))
        }
        return mApplicationProvider!![modelClass]
    }

    protected open fun <T : ViewModel?> getApplicationScopeViewModel(modelClass: Class<T>): T {
        if (mApplicationProvider == null) {
            mApplicationProvider = ViewModelProvider(BaseApplication.getInstance(),AndroidViewModelFactory(application))
        }
        return mApplicationProvider!![modelClass]
    }
}