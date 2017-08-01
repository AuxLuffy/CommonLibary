package com.lenovo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class AppStartActivity : Activity() {
    val animTime = 1500L
    fun start(time: Long) {
        val anim = ObjectAnimator.ofFloat(startIcon, "alpha", 1.0f, 0.5f)
                .setDuration(time)
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                jump()
            }
        })
        anim.start()
    }

    private fun jump() {
        startActivity<HomeActivity>()
        finish()
    }

    val appUI = AppStartActivityUI()
    val startIcon by lazy {
        appUI.startIcon
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUI.setContentView(this)
        start(animTime)
    }
}
