package com.kevin.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.kevin.common.constance.RoutePath
import com.kevin.common.net.ApiServer
import com.kevin.libbase.net.RequestModel
import com.kevin.libbase.net.exception.ApiException
import com.kevin.libbase.net.response.ICallBack
import com.kevin.mvxapp.module.BannerModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@Route(path = RoutePath.MAIN_ACTIVITY)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var apiServer: ApiServer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RequestModel.request(apiServer.test(), this, object : ICallBack<List<BannerModel>> {
            override fun onSuccess(data: List<BannerModel>) {
                Log.d("MainActivity--", "retrofit:${data}")
            }

            override fun onFailure(apiException: ApiException) {
                Log.d("MainActivity--", "onFailure:retrofit:${apiException.errorMsg}")
            }
        })
    }
}