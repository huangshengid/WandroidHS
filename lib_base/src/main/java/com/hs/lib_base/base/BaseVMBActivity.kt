package com.hs.lib_base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType
import com.hs.lib_base.BR
import com.hs.lib_base.R
import com.hs.lib_base.ext.hideLoading
import com.hs.lib_base.utils.LogUtil
import com.hs.lib_base.utils.ToastUtil
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @Author      : huangsheng
 * @Email       : 942698056@qq.com
 * @Date        : on 2023-09-01 23:27.
 * @Description :描述
 */
abstract class BaseVMBActivity<VM : BaseViewModel,B : ViewDataBinding>(private val contentViewResId : Int) :
    AppCompatActivity() {
        lateinit var mViewModel : VM
        lateinit var mBinding : B


        open fun setFullScreen() :Boolean{
            return false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置沉浸式状态栏，由于启动页SplashActivity需要无状态栏，这里写死不太好
        // 直接在主题里将其他的状态栏颜色写成跟ActionBar相同，而启动页则是无状态栏
        // 或者提供一个修改的api让SplashActivity重写，两者均可（假如需要更换主题用代码设置更灵活）
        initViewModel()
        initDataBinding()
        createObserve()
        initView(savedInstanceState)
    }

    /** ViewModel初始化 */
    @Suppress("UNCHECKED_CAST")
    private fun initViewModel() {
        val type: Class<VM> =
            (this.javaClass.genericInterfaces as ParameterizedType).actualTypeArguments[0] as Class<VM>
        mViewModel = ViewModelProvider(this)[type]
        mViewModel.start()
    }

    /** DataBinding初始化 */
    private fun initDataBinding() {
        mBinding = DataBindingUtil.setContentView(this, contentViewResId)
        mBinding.apply {
            // 需绑定lifecycleOwner到activity,xml绑定的数据才会随着liveData数据源的改变而改变
            lifecycleOwner = this@BaseVMBActivity
            setVariable(BR.viewModel, mViewModel)
        }
    }

    /** View相关初始化 */
    open fun initView(savedInstanceState: Bundle?){}

    /** 提供编写LiveData监听逻辑的方法 */
    open fun createObserve() {
        //全局服务器请求错误监听
        mViewModel.apply {
            exception.observe(this@BaseVMBActivity, Observer {
                requestError(it.message)
                LogUtil.e("网络请求错误 ${it.message}")
                when (it){
                    is SocketTimeoutException -> ToastUtil.showShort(
                        this@BaseVMBActivity,getString(R.string.response_error)
                    )
                    is ConnectException,is UnknownHostException -> ToastUtil.showShort(
                        this@BaseVMBActivity,getString(R.string.network_error)
                    )

                    else -> ToastUtil.showShort(this@BaseVMBActivity,
                    it.message ?: getString(R.string.response_error))
                }
            })

            // 全局服务器返回的错误信息监听
            errorResponse.observe(this@BaseVMBActivity, Observer {
                requestError(it?.errorMsg)
                it?.errorMsg?.run {
                    ToastUtil.showShort(this@BaseVMBActivity,this)
                }
            })
        }

    }

    /** 提供一个请求错误的方法,用于像关闭加载框之类的 */
    open fun requestError(msg : String?){
        hideLoading()
    }


}