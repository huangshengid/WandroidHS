package com.hs.wandroid.ui.login

import android.content.Context
import android.content.Intent
import com.hs.lib_base.base.BaseVMBActivity
import com.hs.wandroid.R
import com.hs.wandroid.databinding.ActivityLoginBinding

/**
 * @Author      : huangsheng
 * @Email       : 942698056@qq.com
 * @Date        : on 2023-09-06 22:44.
 * @Description :描述
 */
class LoginActivity : BaseVMBActivity<LoginViewModel,ActivityLoginBinding>(R.layout.activity_login){

    companion object{
        /**
         * 页面启动
         * @param context Context
         */
        fun launch(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }

}