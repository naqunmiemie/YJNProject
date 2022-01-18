package com.yjn.common;

import android.app.Application;
import android.view.Gravity;

import com.hjq.toast.ToastUtils;


public class Common {
    public static void init(Application application){
        ToastUtils.init(application);
        ToastUtils.setGravity(Gravity.BOTTOM,0,150);

    }
}
