package com.yjn.yjnproject.ui.activity

import android.os.Handler
import com.yjn.yjnproject.databinding.ActivityLeakBinding
import com.yjn.common.base.BaseActivity

class LeakActivity : BaseActivity<ActivityLeakBinding>() {
    private val mHandler: Handler = Handler(mainLooper) {
        binding.tvText.text = "hello world"
        false
    }

    override fun initView() {
        mHandler.sendEmptyMessageDelayed(0,20*60*60*1000)
        finish()
    }

    override fun initData() {

    }
}