package com.lenovo

import android.app.Activity
import android.os.Bundle
import org.jetbrains.anko.setContentView

class AboutActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AboutActivityUI().setContentView(this)
    }
}
