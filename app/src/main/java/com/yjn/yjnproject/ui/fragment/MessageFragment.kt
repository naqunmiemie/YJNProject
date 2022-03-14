package com.yjn.yjnproject.ui.fragment

import android.content.Intent
import com.blankj.utilcode.util.ActivityUtils
import com.tencent.bugly.crashreport.CrashReport
import com.yjn.common.util.L
import com.yjn.common.util.T
import com.yjn.yjnproject.App
import com.yjn.yjnproject.databinding.FragmentMessageBinding
import com.yjn.yjnproject.ui.activity.LeakActivity
import com.yjn.yjnproject.ui.base.BaseFragment

class MessageFragment : BaseFragment<FragmentMessageBinding>() {

    companion object {
        @JvmStatic
        fun newInstance() = MessageFragment()
    }

    override fun initView() {
        binding.btnThrashing.setOnClickListener {
            var tmp = ""
            for (i in 0..9999){
                tmp += "a"
            }
            T.show("用String拼接10000个字符串")
        }
        binding.btnLeaking.setOnClickListener {
            val intent = Intent()
            context?.let { it1 -> intent.setClass(it1,LeakActivity::class.java) }
            ActivityUtils.startActivity(intent)
            T.show("Activity持有Handler造成的泄漏")
        }
        binding.btnTestCrash.setOnClickListener {
            L.e("testJavaCrash")
            CrashReport.testJavaCrash()
        }

        binding.btnTestAnr.setOnClickListener {
            Thread.sleep(20*1000)
        }
    }

    override fun initData() {
    }
}