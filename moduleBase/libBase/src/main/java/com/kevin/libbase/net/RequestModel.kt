package com.kevin.libbase.net

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.kevin.libbase.net.exception.ApiException
import com.kevin.libbase.net.response.ICallBack
import com.kevin.libbase.net.response.IResponse
import com.kevin.libbase.net.response.ResponseTransformer

/**
 * created by Kevin
 * on 2021/8/19
 * des: 网络请求的model
 */
class RequestModel {

    companion object {
        /**
         * 发起网络请求的通用方法 Observable<IResponse>对象 ,ICallBack, LifeCycleOwner对象
         * compose操作符结合Transformer调度器  复用了复用Transformers 的代码
         *  数据请求成功 返回的DATA类型
         *  失败了包含了 请求是回调失败和RxJava内部失败
         */
        @SuppressLint("CheckResult")
        fun <DATA> request(
            call: io.reactivex.Observable<out IResponse<DATA>>, lifecycleOwner: LifecycleOwner?,
            callBack: ICallBack<DATA>
        ) {
            call.compose(ResponseTransformer.obtain(lifecycleOwner))
                .subscribe(
                    { data ->
                        callBack.onSuccess(data)
                        Log.d("net---", "请求成功:${data}")
                    }
                ) { e ->
                    callBack.onFailure(ApiException.handleException(e))
                    Log.d("net---", "请求失败:${e.message}")
                }

        }
    }
}