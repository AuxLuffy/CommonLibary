package com.lenovo

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.customView

/**
 * Created by Administrator on 2017/8/2.
 */
class UI {
    companion object {
        fun commTitleBar(ctx: Context): View = ctx.relativeLayout {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip(45))
            backgroundColor = resources.getColor(R.color.red_EA0000)
            fitsSystemWindows = true
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                imageView(R.drawable.back_arrow) {
                    setPadding(dip(15), 0, dip(10), 0)
                }.lparams(width = dip(35), height = dip(20))
                view {
                    backgroundColor = resources.getColor(R.color.color_line)
                }.lparams(width = 1, height = dip(30))
                textView(R.string.back) {
                    textColor = resources.getColor(R.color.black)
                    textSize = 16F
                    setPadding(dip(10), 0, 0, 0)
                }.lparams(width = wrapContent, height = wrapContent)
            }.lparams(width = wrapContent, height = matchParent) {

            }
        }

        fun splitLine(ctx: Context): View = ctx.customView<View> {
            backgroundColor = resources.getColor(R.color.appbg)
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1)
        }
    }
}