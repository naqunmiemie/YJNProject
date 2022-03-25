package com.yjn.common.util

import com.blankj.utilcode.util.AppUtils
import com.yjn.common.util.L
import android.text.TextUtils
import android.util.Log

object L {
    private val tagPrefix =
        AppUtils.getAppPackageName().split("\\.").toTypedArray()[AppUtils.getAppPackageName()
            .split("\\.").toTypedArray().size - 1] + "Log"
//    private val isShow = AppUtils.isAppDebug()
    private const val isShow = true
    private val showV: Boolean = isShow
    private val showD: Boolean = isShow
    private val showI: Boolean = isShow
    private val showW: Boolean = isShow
    private val showE: Boolean = isShow
    private val showWTF: Boolean = isShow

    /**
     * 得到tag（所在类.方法（L:行））
     * @return
     */
    private fun generateTag(): String {
        val stackTraceElement = Thread.currentThread().stackTrace[4]
        var callerClazzName = stackTraceElement.className
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1)
        var tag = "%s.%s(L:%d)"
        tag = String.format(
            tag,
            *arrayOf<Any>(
                callerClazzName,
                stackTraceElement.methodName,
                Integer.valueOf(stackTraceElement.lineNumber)
            )
        )
        //给tag设置前缀
        tag = if (TextUtils.isEmpty(tagPrefix)) tag else tagPrefix + ":" + tag
        return tag
    }

    fun v(msg: String?) {
        if (showV) {
            val tag = generateTag()
            Log.v(tag, msg!!)
        }
    }

    fun v(msg: String?, tr: Throwable?) {
        if (showV) {
            val tag = generateTag()
            Log.v(tag, msg, tr)
        }
    }

    fun d(msg: String?) {
        if (showD) {
            val tag = generateTag()
            Log.d(tag, msg!!)
        }
    }

    fun d(msg: String?, tr: Throwable?) {
        if (showD) {
            val tag = generateTag()
            Log.d(tag, msg, tr)
        }
    }

    fun i(msg: String?) {
        if (showI) {
            val tag = generateTag()
            Log.i(tag, msg!!)
        }
    }

    fun i(msg: String?, tr: Throwable?) {
        if (showI) {
            val tag = generateTag()
            Log.i(tag, msg, tr)
        }
    }

    fun w(msg: String?) {
        if (showW) {
            val tag = generateTag()
            Log.w(tag, msg!!)
        }
    }

    fun w(msg: String?, tr: Throwable?) {
        if (showW) {
            val tag = generateTag()
            Log.w(tag, msg, tr)
        }
    }

    fun e(msg: String?) {
        if (showE) {
            val tag = generateTag()
            Log.e(tag, msg!!)
        }
    }

    fun e(msg: String?, tr: Throwable?) {
        if (showE) {
            val tag = generateTag()
            Log.e(tag, msg, tr)
        }
    }

    fun wtf(msg: String?) {
        if (showWTF) {
            val tag = generateTag()
            Log.wtf(tag, msg)
        }
    }

    fun wtf(msg: String?, tr: Throwable?) {
        if (showWTF) {
            val tag = generateTag()
            Log.wtf(tag, msg, tr)
        }
    }
}