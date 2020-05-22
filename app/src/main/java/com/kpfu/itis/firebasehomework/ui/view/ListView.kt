package com.kpfu.itis.firebasehomework.ui.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ListView: MvpView {

    fun openDialog()

    fun closeDialog()

    fun initRecycler()

    fun goToAuth()

    fun initAdd()
}