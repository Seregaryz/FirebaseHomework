package com.kpfu.itis.firebasehomework.service

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegistrationServiceImpl @Inject constructor(
    private var auth: FirebaseAuth
): RegistrationService {

    override fun createAccount(email: String, password: String): Boolean {
        var res = false
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    res = true
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                }
            }
        return res
    }
}