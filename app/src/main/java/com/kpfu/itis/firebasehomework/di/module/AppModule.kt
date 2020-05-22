package com.kpfu.itis.firebasehomework.di.module

import android.app.Application
import android.content.Context
import com.kpfu.itis.firebasehomework.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule  {

    @Provides
    @Singleton
    fun provideContext(application: App): Context = application.applicationContext
}