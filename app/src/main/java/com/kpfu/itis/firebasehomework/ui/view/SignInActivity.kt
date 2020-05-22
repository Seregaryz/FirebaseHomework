package com.kpfu.itis.firebasehomework.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.material.snackbar.Snackbar
import com.kpfu.itis.firebasehomework.App
import com.kpfu.itis.firebasehomework.R
import com.kpfu.itis.firebasehomework.di.Injector
import com.kpfu.itis.firebasehomework.ui.presenter.SignInPresenter
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_in.et_email
import kotlinx.android.synthetic.main.activity_sign_in.et_password
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class SignInActivity : MvpAppCompatActivity(), SignInView, GoogleApiClient.OnConnectionFailedListener {

    @Inject
    lateinit var presenterProvider: Provider<SignInPresenter>

    private val presenter: SignInPresenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Injector.plusAuthenticationComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initListeners()
    }

    private fun initListeners(){
        btn_auth.setOnClickListener{
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            presenter.signIn(email, password)
        }
        btn_google_sign_in.setOnClickListener {
            presenter.signInWithGoogle()
        }
        auth_btn_sign_up.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    override fun navigateToList() {
        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    override fun showSignInError() {
        Snackbar.make(findViewById(android.R.id.content), "Sign in error", Snackbar.LENGTH_SHORT).show()
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Snackbar.make(findViewById(android.R.id.content), "Google sign in error", Snackbar.LENGTH_SHORT).show()
    }
}
