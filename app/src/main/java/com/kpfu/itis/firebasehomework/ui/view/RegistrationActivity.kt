package com.kpfu.itis.firebasehomework.ui.view

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.kpfu.itis.firebasehomework.App
import com.kpfu.itis.firebasehomework.R
import com.kpfu.itis.firebasehomework.di.Injector
import com.kpfu.itis.firebasehomework.ui.presenter.RegistrationPresenter
import kotlinx.android.synthetic.main.activity_registration.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class RegistrationActivity: MvpAppCompatActivity(), RegistrationView {

    @Inject
    lateinit var presenterProvider: Provider<RegistrationPresenter>

    private val presenter: RegistrationPresenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.plusAuthenticationComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initListeners()
    }

    private fun initListeners() {
        btn_sign_up.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            presenter.createAccount(email, password)
        }
        reg_btn_sign_in.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }

    override fun navigateToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    override fun showRegisterError() {
        Snackbar.make(findViewById(android.R.id.content), "Sign up error", Snackbar.LENGTH_SHORT).show()
    }
}
