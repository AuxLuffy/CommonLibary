package com.lenovo

import android.view.Gravity
import android.widget.ImageView
import android.widget.RelativeLayout
import org.jetbrains.anko.*

/**
 * Created by Administrator on 2017/8/1.
 */
class AppStartActivityUI : AnkoComponent<AppStartActivity> {
    lateinit var startIcon: ImageView

    companion object {
        val startIconId = 1
    }

    override fun createView(ui: AnkoContext<AppStartActivity>) = ui.apply {
        relativeLayout {
            backgroundColor = R.color.abc_background_cache_hint_selector_material_dark
            layoutParams = RelativeLayout.LayoutParams(matchParent, matchParent)
            gravity = Gravity.CENTER_HORIZONTAL
            startIcon = imageView(R.drawable.ic_launcher) {
                id = startIconId
            }.lparams(dip(100), dip(100)) {
                topMargin = dip(127)
                alignParentTop()
                centerHorizontally()
            }
            textView(R.string.app_name) {
                textSize = sp(20).toFloat()
                gravity = Gravity.CENTER
            }.lparams(matchParent, wrapContent) {
                topMargin = dip(21)
                below(startIconId)
            }
        }
    }.view
}