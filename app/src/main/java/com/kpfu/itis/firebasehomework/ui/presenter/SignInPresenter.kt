package com.kpfu.itis.firebasehomework.ui.presenter

import com.kpfu.itis.firebasehomework.service.SignInServiceImpl
import com.kpfu.itis.firebasehomework.ui.view.SignInView
import moxy.MvpPresenter
import javax.inject.Inject

class SignInPresenter @Inject constructor(
    private var service: SignInServiceImpl
) : MvpPresenter<SignInView>()  {

    fun signIn(email: String, password: String){
        if(service.signIn(email, password)){
            viewState.navigateToList()
        } else {
            viewState.showSignInError()
        }
    }

    fun signInWithGoogle(){
        service.signInWithGoogle()
    }
}