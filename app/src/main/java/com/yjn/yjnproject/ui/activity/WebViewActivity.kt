package com.yjn.yjnproject.ui.activity

import com.yjn.yjnproject.databinding.ActivityWebViewBinding
import com.yjn.common.base.BaseActivity

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {
    companion object{
        const val url = "URL"
    }

    override fun initView() {
        intent.extras?.getString(url)?.let { binding.wvWebView.loadUrl(it) }

    }

    override fun initData() {
    }


}