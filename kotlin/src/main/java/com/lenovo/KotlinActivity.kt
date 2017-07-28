package com.lenovo

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.net.URL

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        //1.通过anko库，去除findViewById操作，直接通过xml中view的id即可获取控件
        //2.点击事件中可以用lambda语法简化操作
        //3.toast是通过类扩展的方式实现的，直接可以在任何直接使用，类似swift和oc的方式
        btnClick.setOnClickListener { toast("这是Kotlin中点击Button，有一点lambda语法") }
        netWork.setOnClickListener { netWork() }
    }

    fun netWork() {
        //1.异步，类似asynctask的操作
        async {
            //子线程，耗时，非UI操作
            h.sendEmptyMessage(0)
            val result = URL("https://www.baidu.com/").readText()
            Thread.sleep(1000)
            h.sendEmptyMessage(1)
            Thread.sleep(1000)
            h.sendEmptyMessage(3)
            Thread.sleep(1000)
            h.sendEmptyMessage(5)
            Thread.sleep(1000)
            uiThread {
                //UI线程，更细UI
                count.text = result
                h.sendEmptyMessage(9)
            }
        }
    }

    val h = Handler {
        when (it.what) {
            0 -> consume { toast("开始网络请求") }
            1, 3, 5 -> consume { toast("请求中。。。") }
            in 7..10 -> consume { toast("请求结束") }
            else -> false
        }
    }

    fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }
}
