package com.example.capfinal

import android.app.Application
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kg.geektech.finalprojectcustomcap.R

class App : Application(){
    private var mGoogleSignInClient: GoogleSignInClient? = null

    fun getGoogleSignInClient(): GoogleSignInClient? {
        return mGoogleSignInClient
    }
    override fun onCreate() {
        super.onCreate()
        createClient()
    }

    private fun createClient() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.your_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }
}