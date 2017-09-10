package com.lenovo

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import org.jetbrains.anko.*
import org.json.JSONObject
import java.net.URL

class HomeActivity : Activity() {
    val loginJSON = "{\"status_code\":200,\"message\":\"success\",\"data\":{\"token\":\"9066be30-6db4-4a5b-afb6-0259e7db2c54\",\"phone\":\"18612141380\",\"name\":\"测试\",\"shop_name\":\"\",\"shop_number\":\"\",\"is_bind\":\"0\",\"is_audit\":\"0\",\"head_img_url\":\"http://img3.imgtn.bdimg.com/it/u=951120017,198117664&fm=26&gp=0.jpg\",\"identity\":\"1\"}}"
    val appUI = HomeActivityUI()
    val ivHead by lazy { appUI.ivHead }
    val tvAbout by lazy { appUI.tvAbout }
    val tvName by lazy { appUI.tvName }
    val tvPhone by lazy { appUI.tvPhone }
    val tvRole by lazy { appUI.tvRole }

    val loginLoadingDialog by lazy {
        progressDialog(message = "登录获取用户信息") {
            setProgressStyle(android.R.style.Widget_DeviceDefault_Light_ProgressBar_Inverse)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appUI.setContentView(this)
        tvAbout.setOnClickListener {
            startActivity(Intent(it.context, AboutActivity::class.java))
        }
        doLogin()
    }

    fun doLogin() {
        showLoading()
        doAsync {
            Thread.sleep(1000)

            val res = convert2Obj(loginJSON, LoginResult::class.java)
            uiThread {
                if (res.status_code == 200) {
                    success(res)
                } else {
                    error(res.message)
                }
                dismissLoading()
            }
        }
    }

    private fun  convert2Obj(loginJSON: String, objClass: Class<LoginResult>): LoginResult {
        return LoginResult(User("","","","",0),200,"")
    }

    private fun error(msg: String) {
        toast(msg)
    }

    private fun success(res: LoginResult) {
        getIcon(res.u.head_img_url)
        tvName.text = res.u.name
        tvPhone.text = res.u.phone
        when (res.u.identity) {
            0 -> tvRole.text = "未知"
            1 -> tvRole.text = "店员"
            2 -> tvRole.text = "店长"
        }
    }


    private fun showLoading() {
        if (!isFinishing) {
            loginLoadingDialog.show()
        }
    }

    private fun dismissLoading() {
        if (!isFinishing) {
            loginLoadingDialog.dismiss()
        }
    }

    fun getIcon(url: String) {
        doAsync {
            val url = URL(url)
            val conn = url.openConnection()
            conn.setRequestProperty("token", "9066be30-6db4-4a5b-afb6-0259e7db2c54")
            val os = url.openStream()
            val bp = BitmapFactory.decodeStream(os)
            uiThread {
                ivHead.imageBitmap = bp
            }
        }
    }

}
