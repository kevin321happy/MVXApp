package com.kevin.libbase.net.response

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.kevin.libbase.net.exception.ApiException
import com.kevin.libbase.utils.ReflectUtil
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * created by Kevin
 * on 2021/8/18
 * des: 响应数据转换,对后台json去壳返回DATA的数据
 * Observable<IResponse<T>>---->Observable<T>
 *    1、实现线程的切换,代码复用
 *    2、对RxJava生命周期管理防止内存泄露,观察Activity的生命周期同步处理事件
 *    3、对响应统一处理获取最终需要的DATA数据返回
 */
class ResponseTransformer<DATA> : ObservableTransformer<IResponse<DATA>, DATA>, LifecycleObserver {
    private val compositeDisposable = CompositeDisposable()

    companion object {
        fun <DATA> obtain(lifecycleOwner: LifecycleOwner?): ResponseTransformer<DATA> {
            return ResponseTransformer<DATA>().apply {
                lifecycleOwner?.lifecycle?.addObserver(this)
            }
        }
    }

    //网络回调

    @SuppressLint("CheckResult", "NewApi")
    override fun apply(upstream: Observable<IResponse<DATA>>): ObservableSource<DATA> {
        return upstream.doOnSubscribe { disposable -> compositeDisposable.add(disposable) }
            .flatMap { response ->
                //对响应数据统一处理
                if (response.isSuccess()) {
                    Log.d("net--","isSuccess:${response.data()}")
                    if (response.data() != null) {
                        Observable.just(response.data())
                    } else {
                        //网络请求成功了但是数据是空的,通过反射手动创建DATA
                        val clz = ReflectUtil.analysisClassInfo(response)
                        val obj: DATA = clz.newInstance() as DATA
                        Observable.just(obj)
                    }
                } else {
                    //请求失败了
                    Log.d("net--","error:${response.data()}")
                    Observable.error(ApiException(response.code(), response.msg()))
                }
            }
            .subscribeOn(Schedulers.io())//指定事件产生的线程(请求的线程)
            .observeOn(AndroidSchedulers.mainThread())// 指定事件处理的线程 (响应的线程)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}

