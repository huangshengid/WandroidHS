package com.hs.wandroid.base

import androidx.databinding.ViewDataBinding
import com.hs.lib_base.base.BaseVMBActivity
import com.hs.lib_base.base.BaseViewModel
import com.hs.wandroid.ui.login.LoginActivity

/**
 * @Author      : huangsheng
 * @Email       : 942698056@qq.com
 * @Date        : on 2023-09-06 22:24.
 * @Description :在这里只做了统一处理跳转登录页面的逻辑
 */
abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding>(contentViewResId: Int) :
    BaseVMBActivity<VM, B>(contentViewResId) {
    override fun createObserve() {
        super.createObserve()
        mViewModel.errorResponse.observe(this){
            if (it?.errorCode == -1001){
                // 需要登录，这里主要是针对收藏操作，不想每个地方都判断一下
                // 当然你也可以封装一个收藏的组件，在里面统一判断跳转
                LoginActivity.launch(this)
            }
        }
    }
}