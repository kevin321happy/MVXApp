package com.kevin.common.net

import com.kevin.libbase.net.response.IResponse

/**
 * created by Kevin
 * on 2021/8/19
 * des:
 */
open class BaseResponse<T> : IResponse<T> {

    var status: Int = 0
    var title: String = ""
    var data: T? = null
    override fun code(): String {
        return status.toString()
    }

    override fun data(): T {
        return data!!
    }

    override fun msg(): String {
        return title
    }

    override fun isSuccess(): Boolean {
        //return status == 100
        return true
    }
}