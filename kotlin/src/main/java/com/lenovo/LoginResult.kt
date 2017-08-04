package com.lenovo

import com.google.gson.annotations.SerializedName

/**
 * Created by Administrator on 2017/8/4.
 */
data class User(
        var token: String = "",
        var phone: String = "",
        var name: String = "",
        var head_img_url: String = "",
        var identity: Int = 0
)

data class LoginResult(@SerializedName("data") var u: User, var status_code:Int, var message:String)