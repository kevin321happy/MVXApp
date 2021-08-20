package com.kevin.libbase.init

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import java.util.*

/**
 * created by Kevin
 * on 2021/8/19
 * des:
 */
class LibBaseInitializer: Initializer<Any> {
    override fun create(context: Context): Any {
        //TODO 各种初始化操作
        Log.i("LibBaseInitializer","OtherInitializer create: ")
        return "初始化完成"
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        val list: MutableList<Class<out Initializer<*>?>> = ArrayList()
        list.add(OtherInitializer::class.java)
        return list
    }


}