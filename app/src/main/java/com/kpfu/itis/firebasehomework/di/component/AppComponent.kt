package com.kpfu.itis.firebasehomework.di.component

import android.content.Context
import com.kpfu.itis.firebasehomework.App
import com.kpfu.itis.firebasehomework.di.module.AppModule
import com.kpfu.itis.firebasehomework.di.module.FirebaseModule
import com.kpfu.itis.firebasehomework.ui.view.ListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FirebaseModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun firebaseModule(firebaseModule: FirebaseModule):Builder
        fun build(): AppComponent
    }

    fun getContext(): Context

    fun plusAuthenticationComponent(): AuthenticationComponent.Builder

}