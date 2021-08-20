package com.kevin.common.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * created by Kevin
 * on 2021/8/19
 * des:基类的Application
 */
open class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
    }
}