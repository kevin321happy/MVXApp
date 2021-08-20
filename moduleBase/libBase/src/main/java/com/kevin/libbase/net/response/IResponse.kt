package com.kevin.libbase.net.response

/**
 * created by Kevin
 * on 2021/8/18
 * des: 响应的接口
 */
interface IResponse<T> {
    fun code():String
    fun data():T
    fun msg():String
    fun isSuccess():Boolean
}