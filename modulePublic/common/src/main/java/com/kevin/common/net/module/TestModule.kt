package com.kevin.mvxapp.module

import com.kevin.common.net.BaseResponse


/**
 * created by Kevin
 * on 2021/8/19
 * des:
 */

data class  BannerModel(val image:String,val title:String,val url:String)

/**
 * 首页的Banner数据
 */
class BannerListModel<BannerModel>: BaseResponse<List<BannerModel>>() {

}