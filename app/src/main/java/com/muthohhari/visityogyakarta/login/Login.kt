package com.muthohhari.visityogyakarta.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.muthohhari.visityogyakarta.R
import com.muthohhari.visityogyakarta.main.Main
import com.muthohhari.visityogyakarta.signup.SignUp
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.mtrl_layout_snackbar_include.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class Login : AppCompatActivity(), View.OnClickListener, AnkoLogger {
    private var mAuth: FirebaseAuth? = null
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 101
    private val message = "snackbar gagal"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createAccountText.setOnClickListener(this)
        loginGoogleButton.setOnClickListener(this)
        loginPasswordButton.setOnClickListener(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.createAccountText -> startActivity<SignUp>()
            R.id.loginPasswordButton -> signIn(editTextEmail.text.toString(), editTextPassword.text.toString())
            R.id.loginGoogleButton -> googleSignIn()
        }

    }

    private fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm(email, password)) {
            return
        }
        mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { it ->
            if (it.isSuccessful) {
                startActivity<Main>()
                finish()
            } else {
                longToast("Authentication Failed")
            }
        }
    }

    private fun validateForm(email: String, password: String): Boolean {
        if (TextUtils.isEmpty(email)) {
            toast("Enter Email Address")
            return false
        }
        if (TextUtils.isEmpty(password)) {
            toast("Enter Password")
            return false
        }
        if (password.length < 6) {
            toast("Password to short, enter minimum 6 characters")
            return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        Log.d("TAG", "firebaseAuthWithGoogle:" + account.id!!)

        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithCredential:success")
                    startActivity<Main>()
                    finish()
                } else {
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    snackbar_text.text = message
                }

                // ...
            }

    }
}