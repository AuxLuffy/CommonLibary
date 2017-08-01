package com.lenovo

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import org.jetbrains.anko.*

class HomeActivity : Activity() {

    val appUI = AppStartActivityUI()
    val startIcon by lazy {
        appUI.startIcon
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verticalLayout {
            relativeLayout {
                backgroundResource = R.drawable.my_tab_bg

                frameLayout {
                    imageView(R.drawable.head).lparams(width = dip(75), height = dip(75)) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    textView(R.string.unkown) {
                        gravity = Gravity.CENTER
                        textColor = android.R.color.white
                        textSize = sp(14).toFloat()
                        backgroundResource = R.drawable.icon_role_tag
                    }.lparams(width = dip(90), height = dip(25)) {
                        topMargin = dip(55)
                        gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    centerVertically()
                    leftMargin = dip(20)
                }
            }.lparams(width = matchParent, height = dip(140)) {

            }
        }
    }

}
