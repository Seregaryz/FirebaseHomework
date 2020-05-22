package com.kpfu.itis.firebasehomework.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface SignInView: MvpView {

    fun navigateToList()

    fun showSignInError()
}