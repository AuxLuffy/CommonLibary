package com.lenovo

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import org.jetbrains.anko.*

/**
 * Created by Administrator on 2017/8/2.
 */
class AboutActivityUI : AnkoComponent<AboutActivity> {

    override fun createView(ui: AnkoContext<AboutActivity>): View = ui.apply {
        linearLayout {
            orientation=LinearLayout.VERTICAL

            addView(UI.commTitleBar(context))
            imageView(R.drawable.loginicon) {
                scaleType = ImageView.ScaleType.FIT_XY
            }.lparams(width = dip(85), height = dip(85)) {
                topMargin = dip(50)
                gravity = Gravity.CENTER_HORIZONTAL
            }
            textView(R.string.app_version) {
                textSize = 15F
                gravity = Gravity.CENTER
            }.lparams(width = dip(85), height = wrapContent) {
                topMargin = dip(5)
                bottomMargin = dip(30)
                gravity = Gravity.CENTER_HORIZONTAL
            }

            addView(UI.splitLine(context).lparams(width = matchParent, height = 1) {
                topMargin = dip(20)
            })
            textView(R.string.check_update) {
                backgroundColor = Color.WHITE
                compoundDrawablePadding = dip(10)
                gravity = Gravity.CENTER_VERTICAL
                textColor = Color.BLACK
                textSize = 14F
                setPadding(dip(10), 0, dip(10), 0)
                setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_right, 0)

            }.lparams(width = matchParent, height = dip(50))

            addView(UI.splitLine(context))
            textView(R.string.us) {
                backgroundColor = Color.WHITE
                gravity = Gravity.CENTER_VERTICAL
                textColor = Color.BLACK
                textSize = 14F
                setPadding(dip(10), 0, dip(10), 0)
            }.lparams(width = matchParent, height = dip(50))

            addView(UI.splitLine(context))

            view().lparams(width = matchParent, height = 0, weight = 1f)
            textView(R.string.copy_right) {
                backgroundColor = Color.WHITE
                gravity = Gravity.CENTER
                textColor = resources.getColor(R.color.gray_999)
                textSize = 11F
                setLineSpacing(1f,1.2f)
            }.lparams(width = matchParent, height = wrapContent) {
                bottomMargin = dip(20)
                leftMargin = dip(20)
                rightMargin = dip(20)
                gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
            }
        }
    }.view

}