package com.kpfu.itis.firebasehomework.service

import android.os.Bundle
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.kpfu.itis.firebasehomework.App
import com.kpfu.itis.firebasehomework.di.Injector
import com.kpfu.itis.firebasehomework.di.Injector.appComponent
import io.fabric.sdk.android.Fabric
import javax.inject.Inject

class SignInServiceImpl @Inject constructor(
    private var auth: FirebaseAuth,
    private var googleApiClient: GoogleApiClient,
    private val firebaseAnalytics: FirebaseAnalytics): SignInService {

    private var adView: AdView? = null

    override fun signIn(email: String, password: String): Boolean {
        //authenticate user
        var res = true
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    res = true
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
        return res
    }

    override fun signOut() {
        auth.signOut()
    }

    override fun authUser(): Boolean {
        return (auth.currentUser !=  null)
    }

    override fun initAdd(adView: AdView) {
        Fabric.with(appComponent.getContext(), Crashlytics())
        val adRequest =
            AdRequest.Builder().build()
        adView.loadAd(adRequest)
        analitics(adRequest)
    }

    override fun analitics(adRequest: AdRequest) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, adRequest.toString())
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    override fun destroyAd() {
        if (adView != null) {
            adView?.destroy()
        }
    }

    override fun signInWithGoogle(): Boolean {
        var res = false
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        val account = GoogleSignIn.getSignedInAccountFromIntent(signInIntent).result
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    res = true
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    //Snackbar.make(binding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                }
            }
        return res
    }

    companion object {
        private const val TAG = "Authorization"
    }
}