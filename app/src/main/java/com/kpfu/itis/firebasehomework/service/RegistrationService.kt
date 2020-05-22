package com.kpfu.itis.firebasehomework.service

interface RegistrationService {

    fun createAccount(email: String, password: String): Boolean
}