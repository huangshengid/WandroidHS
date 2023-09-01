package com.hs.wandroid.base

import com.hs.lib_base.BaseApp

class App : BaseApp() {

    companion object {
        lateinit var appViewModel: AppViewModel
    }

    override fun onCreate() {
        super.onCreate()
        appViewModel = getAppViewModelProvider()[AppViewModel::class.java]

        // bugly初始化
//        Bugly.init(applicationContext, "99ff7c64d9", false)
    }

}