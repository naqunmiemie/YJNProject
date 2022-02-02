package com.yjn.yjnproject.ui.fragment

import com.yjn.common.util.L
import com.yjn.yjnproject.databinding.FragmentDiscoverBinding
import com.yjn.yjnproject.ui.base.BaseFragment


class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {
    companion object {
        @JvmStatic
        fun newInstance() = DiscoverFragment()
    }

    override fun initView() {
        L.d("!!!")
    }

    override fun initData() {
    }
}