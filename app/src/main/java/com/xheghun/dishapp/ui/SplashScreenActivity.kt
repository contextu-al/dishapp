package com.xheghun.dishapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.xheghun.dishapp.MainActivity
import com.xheghun.dishapp.R
import com.xheghun.dishapp.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreenBinding: ActivitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(splashScreenBinding.root)

        animateIn(splashScreenBinding.tvAppName)
    }

    fun animateIn(view: View) {
        val splashAnim = AnimationUtils.loadAnimation(this,R.anim.anim_splash)
        view.animation = splashAnim


        splashAnim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
               Handler(Looper.getMainLooper()).postDelayed({
                   startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                   finish()
               },1000)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }
}