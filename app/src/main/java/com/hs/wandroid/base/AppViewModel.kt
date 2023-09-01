package com.hs.wandroid.base

import androidx.lifecycle.MutableLiveData
import com.hs.lib_base.base.BaseViewModel
import com.hs.wandroid.data.bean.CollectData
import com.hs.wandroid.data.bean.User

/**
 * @Author      : huangsheng
 * @Email       : 942698056@qq.com
 * @Date        : on 2023-09-01 22:27.
 * @Description :描述
 */
class AppViewModel : BaseViewModel() {
    override fun start() {}

    /** 全局用户 */
    val userEvent = MutableLiveData<User?>()

    /** 分享添加文章 */
    val shareArticleEvent = MutableLiveData<Boolean>()

    /** 文章收藏 */
    val collectEvent = MutableLiveData<CollectData>()

}