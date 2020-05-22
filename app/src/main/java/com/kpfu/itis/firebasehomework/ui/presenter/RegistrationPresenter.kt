package com.kpfu.itis.firebasehomework.ui.presenter

import com.kpfu.itis.firebasehomework.service.RegistrationServiceImpl
import com.kpfu.itis.firebasehomework.ui.view.RegistrationView
import moxy.MvpPresenter
import javax.inject.Inject

class RegistrationPresenter @Inject constructor(
        private var service: RegistrationServiceImpl
) : MvpPresenter<RegistrationView>()  {

        fun createAccount(email: String, password: String){
                if(service.createAccount(email, password)) {
                        viewState.navigateToSignIn()
                } else {
                        viewState.showRegisterError()
                }
        }
}