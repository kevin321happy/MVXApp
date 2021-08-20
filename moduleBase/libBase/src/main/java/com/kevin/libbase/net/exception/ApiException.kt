package com.kevin.libbase.net.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * created by Kevin
 * on 2021/8/18
 * des: Api请求异常
 */
class ApiException(code: String, errorMsg: String) : Exception() {

    var code: String = ""
    var errorMsg: String = ""

    constructor() : this("", "")

    companion object {
        private const val UNKNOWN_ERROR = -0x10
        private const val PARSE_ERROR = -0x11
        private const val NETWORK_ERROR = -0x12

        fun handleException(e: Throwable): ApiException {
            if (e is ApiException) {
                return e
            }
            val ex: ApiException
            return if (e is JsonParseException
                || e is JSONException
                || e is ParseException
            ) {
                ex = ApiException(ApiException.PARSE_ERROR.toString(), "数据解析异常")
                ex
            } else if (e is ConnectException
                || e is UnknownHostException
                || e is SocketTimeoutException
            ) {
                ex = ApiException(ApiException.NETWORK_ERROR.toString(), "网络请求异常")
                ex
            } else {
                ex = ApiException(ApiException.UNKNOWN_ERROR.toString(), "其它异常：" + e.message)
                e.printStackTrace()
                ex
            }
        }
    }
}