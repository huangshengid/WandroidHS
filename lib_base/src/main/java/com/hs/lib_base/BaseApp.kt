package com.hs.lib_base

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.tencent.mmkv.MMKV
import kotlin.properties.Delegates

open class BaseApp : Application(),ViewModelStoreOwner {

    //lateinit代表声明非空对象
    private lateinit var mAppViewModelStore : ViewModelStore
    private var mFactory : ViewModelProvider.Factory? = null


    companion object{
        var appContext : Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        mAppViewModelStore = ViewModelStore()

        // MMKV初始化
        MMKV.initialize(this)

    }

    fun getAppViewModelProvider(): ViewModelProvider{
        return ViewModelProvider(this,)
    }

    private fun getAppViewModelFactory() : ViewModelProvider.Factory{
        if(mFactory == null){
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }
}


//1、ViewModelProvider其实只是一个中介：
//
//ViewModel的创建，工厂ViewModelProvider.Factory实际是由ComponentActivity持有。
//
//ViewModel的存储，ViewModelStore来负责并且由ComponentActivity持有。
//
//ViewModel的销毁，还是由ComponentActivity来负责。
//
//2、ViewModel就是MVVM中的核心VM，由ComponentActivity来负责生命周期和存储。
//
//3、ComponentActivity就是ViewModel的全权大管家。
//
//4、MVVM中的view层是持有viewmodel层引用的。
