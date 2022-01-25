package com.yjn.yjnproject.ui.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.yjn.yjnproject.App
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB :ViewBinding> : Fragment() {
    private var mFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null
    /** 根布局 */
    private var _binding : VB? = null
    val binding :VB get() = _binding!!
    /** 当前是否加载过 */
    private var loading: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type = javaClass.genericSuperclass
        val clazz = (type as ParameterizedType).actualTypeArguments[0] as Class<VB>
        val method = clazz.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        _binding = method.invoke(null, layoutInflater, container, false) as VB
        loading = false
        initView()
        return _binding!!.root
    }

    override fun onResume() {
        super.onResume()
        if (!loading) {
            loading = true
            initData()
            return
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    /**
     * 这个 Fragment 是否已经加载过了
     */
    open fun isLoading(): Boolean {
        return loading
    }
    /**
     * 初始化控件
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    protected open fun <T : ViewModel?> getFragmentScopeViewModel(modelClass: Class<T>): T {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider!![modelClass]
    }

    protected open fun <T : ViewModel?> getActivityScopeViewModel(modelClass: Class<T>): T {
        if (mActivityProvider == null && activity != null) {

            mActivityProvider = activity?.let {
                ViewModelProvider(it, ViewModelProvider.AndroidViewModelFactory(App.getInstance()))
            }
        }
        return mApplicationProvider!![modelClass]
    }

    protected open fun <T : ViewModel?> getApplicationScopeViewModel(modelClass: Class<T>): T {
        if (mApplicationProvider == null) {
            mApplicationProvider = ViewModelProvider(
                App.getInstance(),
                ViewModelProvider.AndroidViewModelFactory(App.getInstance())
            )
        }
        return mApplicationProvider!![modelClass]
    }
}