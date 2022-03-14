package com.yjn.common

import android.app.Application
import android.view.Gravity
import com.hjq.toast.ToastUtils
import com.tencent.bugly.crashreport.CrashReport


object Common {
    fun init(application: Application?) {
        ToastUtils.init(application)
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 150)

        // Bugly 异常捕捉
        CrashReport.initCrashReport(application, "065923e7ae", false)
    }
}