package com.kpfu.itis.firebasehomework.di

import com.kpfu.itis.firebasehomework.App
import com.kpfu.itis.firebasehomework.di.component.AppComponent
import com.kpfu.itis.firebasehomework.di.component.DaggerAppComponent
import com.kpfu.itis.firebasehomework.di.component.AuthenticationComponent
import com.kpfu.itis.firebasehomework.di.module.FirebaseModule
import com.kpfu.itis.firebasehomework.ui.view.SignInActivity

object Injector {

    lateinit var appComponent: AppComponent
    var authenticationComponent: AuthenticationComponent? = null

    fun init(app: App) {
        appComponent = DaggerAppComponent.builder()
            .application(app)
            .build()
    }

    fun plusAuthenticationComponent(): AuthenticationComponent = authenticationComponent
        ?: appComponent
            .plusAuthenticationComponent()
            .build().also {
                authenticationComponent = it
            }

    fun plusAuthenticationComponent(signInActivity: SignInActivity): AuthenticationComponent = authenticationComponent
        ?: appComponent
            .plusAuthenticationComponent()
            .signInActivity(signInActivity)
            .build().also {
                authenticationComponent = it
            }

    fun clearAuthenticationComponent() {
        authenticationComponent = null
    }
}
