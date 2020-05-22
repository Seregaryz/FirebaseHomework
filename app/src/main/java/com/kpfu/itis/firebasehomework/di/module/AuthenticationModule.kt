package com.kpfu.itis.firebasehomework.di.module

import android.content.Context
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.kpfu.itis.firebasehomework.R
import com.kpfu.itis.firebasehomework.di.ActivityScope
import com.kpfu.itis.firebasehomework.service.RegistrationService
import com.kpfu.itis.firebasehomework.service.RegistrationServiceImpl
import com.kpfu.itis.firebasehomework.service.SignInService
import com.kpfu.itis.firebasehomework.service.SignInServiceImpl
import com.kpfu.itis.firebasehomework.ui.view.SignInActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthenticationModule {

    @Provides
    @ActivityScope
    fun provideSignInService(service: SignInServiceImpl): SignInService = service

    @Provides
    @ActivityScope
    fun provideRegistrationService(service: RegistrationServiceImpl): RegistrationService = service

    @Provides
    @ActivityScope
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @ActivityScope
    fun provideGoogleSignInOptions(): GoogleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.default_web_client_id.toString())
            .requestEmail()
            .build()

    @Provides
    @ActivityScope
    fun provideGoogleSignInClient(context: Context, gso: GoogleSignInOptions): GoogleSignInClient =
        GoogleSignIn.getClient(context, gso)

    @Provides
    @ActivityScope
    fun provideGoogleApiClient(gso: GoogleSignInOptions, activity: SignInActivity): GoogleApiClient =
        GoogleApiClient.Builder(activity)
            .enableAutoManage(activity, activity)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()



}
