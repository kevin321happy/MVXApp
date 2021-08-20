package com.kevin.libbase.init
import android.content.Context
import android.util.Log
import androidx.startup.Initializer



/**
 * created by Kevin
 * on 2021/8/19
 * des:
 */
class OtherInitializer: Initializer<Any> {
    override fun create(context: Context): Any {
        Log.i("OtherInitializer ","OtherInitializer create:")
        return ""
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}