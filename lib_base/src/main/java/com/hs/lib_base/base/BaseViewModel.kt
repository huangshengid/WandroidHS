package com.hs.lib_base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hs.lib_base.data.bean.ApiResponse

/**
 * ViewModel基类
 */
//申明抽象函数，则必须是抽象类
abstract class BaseViewModel : ViewModel() {

    /** 请求异常（服务器请求失败，譬如：服务器连接超时等） */
    val exception = MutableLiveData<Exception>()

    /** 请求服务器返回错误（服务器请求成功但status错误，譬如：登录过期等） */
    val errorResponse = MutableLiveData<ApiResponse<*>>()  //<*>代表泛型

    abstract fun start()

}