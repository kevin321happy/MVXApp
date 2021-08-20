package com.kevin.libbase.net

import io.reactivex.Observer
import retrofit2.http.GET

/**
 * created by Kevin
 * on 2021/8/18
 * des:api接口
 */
interface ApiInterface {

    @GET("XXXxXX")
    fun getUserInfo(): Observer<String>
}