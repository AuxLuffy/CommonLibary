package com.lenovo

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.setContentView

class HomeActivity : Activity() {

    val appUI = HomeActivityUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUI.setContentView(this)
    }

}
