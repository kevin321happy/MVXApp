package com.kevin.common.net;


import com.kevin.mvxapp.module.BannerListModel;
import com.kevin.mvxapp.module.BannerModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * created by Kevin
 * on 2021/8/19
 * des:
 */
public interface ApiServer {

   // @GET("/posts")
    @GET("v2/banners")
    Observable<BannerListModel<BannerModel>> test();
}
