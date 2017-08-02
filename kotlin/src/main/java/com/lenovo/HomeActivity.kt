package com.lenovo

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.imageBitmap
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.uiThread
import java.net.URL

class HomeActivity : Activity() {

    val appUI = HomeActivityUI()
    val ivHead by lazy { appUI.ivHead }
    val tvAbout by lazy { appUI.tvAbout }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUI.setContentView(this)
        tvAbout.setOnClickListener {
            startActivity(Intent(it.context, AboutActivity::class.java))
        }
        getIcon()
    }

    fun getIcon() {
        doAsync {
            val url = URL("http://v1.qzone.cc/avatar/201507/15/18/33/55a636e158719534.jpg%21200x200.jpg")
            val conn = url.openConnection()
            conn.setRequestProperty("token", "123")
            val os = url.openStream()
            val bp = BitmapFactory.decodeStream(os)
            uiThread {
                ivHead.imageBitmap = bp
            }
        }
    }

}
