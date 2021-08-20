package com.kevin.libbase.net.response

import com.kevin.libbase.net.exception.ApiException

/**
 * created by Kevin
 * on 2021/8/19
 * des: 网络请求最终的回调接口
 */
interface ICallBack<DATA> {
    fun onSuccess(data: DATA)
    fun onFailure(apiException: ApiException)
}