package com.kpfu.itis.firebasehomework.di.component

import com.kpfu.itis.firebasehomework.di.ActivityScope
import com.kpfu.itis.firebasehomework.di.module.AuthenticationModule
import com.kpfu.itis.firebasehomework.ui.view.ListActivity
import com.kpfu.itis.firebasehomework.ui.view.RegistrationActivity
import com.kpfu.itis.firebasehomework.ui.view.SignInActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [AuthenticationModule::class])
interface AuthenticationComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun signInActivity(signInActivity: SignInActivity): Builder
        fun build(): AuthenticationComponent
    }

    fun inject(activity: ListActivity)

    fun inject(activity: SignInActivity)

    fun inject(activity: RegistrationActivity)

}
