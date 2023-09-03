package com.hs.lib_base.base

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * @Author      : huangsheng
 * @Email       : 942698056@qq.com
 * @Date        : on 2023-09-03 22:34.
 * @Description :描述
 */
abstract class BaseVMBFragment <VM:BaseViewModel,B : ViewDataBinding> (private val contentViewResId:Int)
    : Fragment() {


}