package com.kpfu.itis.firebasehomework.service

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

interface SignInService {

    fun signIn(email: String, password: String): Boolean

    fun signInWithGoogle(): Boolean

    fun signOut()

    fun authUser(): Boolean

    fun initAdd(adView: AdView)

    fun analitics(adRequest: AdRequest)

    fun destroyAd()

}