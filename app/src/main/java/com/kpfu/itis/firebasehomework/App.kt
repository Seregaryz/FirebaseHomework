package com.kpfu.itis.firebasehomework

import android.app.Application
import com.kpfu.itis.firebasehomework.di.Injector
import moxy.MvpFacade

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MvpFacade.init()
        Injector.init(this)
    }

}