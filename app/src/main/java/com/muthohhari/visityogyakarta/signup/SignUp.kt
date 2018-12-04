package com.muthohhari.visityogyakarta.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.muthohhari.visityogyakarta.R
import com.muthohhari.visityogyakarta.main.Main
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SignUp : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        createAccountButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                createAccount(editTextEmail.text.toString(),editTextUsername.text.toString(),editTextPassword.text.toString())
            }

        })

        mAuth = FirebaseAuth.getInstance()
    }

    private fun createAccount(email: String, user: String, password: String) {
        if (!validateForm(email,user,password)){
            return
        }
        mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){it ->
            if (it.isSuccessful){
                startActivity<Main>()
                finish()
            }else{
                longToast("Filed to create account")
            }
        }
    }

    private fun validateForm(email: String, user:String, password: String):Boolean {
        if(TextUtils.isEmpty(email)){
            toast("Enter Email Address")
            return false
        }
        if(TextUtils.isEmpty(user)){
            toast("Enter User Name")
            return false
        }
        if (TextUtils.isEmpty(password)){
            toast("Enter Password")
            return false
        }
        if (password.length < 6){
            toast("Password to short, enter minimum 6 characters")
            return false
        }
        return true
    }
}
