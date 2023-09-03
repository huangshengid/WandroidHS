package com.hs.lib_base.ext

import android.annotation.SuppressLint
import com.afollestad.materialdialogs.MaterialDialog

/**
 * @Author      : huangsheng
 * @Email       : 942698056@qq.com
 * @Date        : on 2023-09-03 21:57.
 * @Description :描述
 */


/** 加载框 */
@SuppressLint("StaticFieldLeak")
private var mLoadingDialog: MaterialDialog? = null

/** 隐藏Loading加载框 */
fun hideLoading() {
    mLoadingDialog?.dismiss()
    mLoadingDialog = null
}