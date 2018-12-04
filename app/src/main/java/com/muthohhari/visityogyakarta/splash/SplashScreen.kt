package com.muthohhari.visityogyakarta.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.muthohhari.visityogyakarta.login.Login
import com.muthohhari.visityogyakarta.main.Main
import com.muthohhari.visityogyakarta.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import org.jetbrains.anko.startActivity


class SplashScreen : AppCompatActivity() {
    private var mAuth : FirebaseAuth? = null
    private lateinit var user:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth!!.currentUser

        object : CountDownTimer(1000, 500) {
            override fun onFinish() {
                root_view.setBackgroundColor(ContextCompat.getColor(this@SplashScreen,
                    R.color.black_opacity
                ))
                if (currentUser != null){
                    startActivity<Main>()
                    finish()
                }else{
                    startActivity<Login>()
                    finish()
                }
            }

            override fun onTick(p0: Long) {}
        }.start()
    }
}
