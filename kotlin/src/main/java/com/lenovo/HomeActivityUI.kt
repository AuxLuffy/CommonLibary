package com.lenovo

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*

/**
 * Created by Administrator on 2017/8/1.
 */
class HomeActivityUI : AnkoComponent<HomeActivity> {
    companion object {
        val tvUserNameId = 1
        val split = tvUserNameId + 1
        val headLayout = split + 1
    }

    override fun createView(ui: AnkoContext<HomeActivity>) = ui.apply {
        verticalLayout {
            backgroundColor = resources.getColor(R.color.appbg)
            val text2 = applyRecursively {
                view ->
                when (view) {
                    is TextView -> {
                        view.singleLine = true
                        view.gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
                        view.textColor = resources.getColor(R.color.gray_999)
                        view.lparams(width = matchParent, height = matchParent) {
                            rightMargin = dip(15)
                        }
                    }
                }
            }
            relativeLayout {
                backgroundResource = R.drawable.my_tab_bg

                frameLayout {
                    id = headLayout
                    imageView(R.drawable.head).lparams(width = dip(75), height = dip(75)) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                    textView(R.string.unkown) {
                        gravity = Gravity.CENTER
                        textColor = Color.WHITE
                        textSize = 14F
                        backgroundResource = R.drawable.icon_role_tag
                    }.lparams(width = dip(90), height = dip(25)) {
                        topMargin = dip(55)
                        gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    centerVertically()
                    leftMargin = dip(20)
                }

                textView(R.string.username) {
                    id = tvUserNameId
                    textSize = 18F
                    typeface = Typeface.DEFAULT_BOLD
                    textColor = Color.WHITE
                    setPadding(dip(10), 0, 0, dip(5))
                }.lparams(width = wrapContent, height = wrapContent) {
                    above(split)
                    rightOf(headLayout)
                }
                view {
                    id = split
                    setPadding(dip(10), 0, 0, 0)
                }.lparams(width = wrapContent, height = 1) {
                    centerVertically()
                    rightOf(headLayout)
                }

                textView(R.string.phone) {
                    textColor = Color.WHITE
                    setPadding(dip(10), dip(5), 0, 0)
                }.lparams(width = wrapContent, height = wrapContent) {
                    sameBottom(headLayout)
                    below(split)
                    rightOf(headLayout)
                }
                relativeLayout {
                    gravity = Gravity.CENTER
                    imageView {
                        scaleType = ImageView.ScaleType.CENTER_INSIDE
                        imageResource = R.drawable.icon_setting
                    }.lparams(width = wrapContent, height = wrapContent)
                }.lparams(width = dip(50), height = dip(50)) {
                    alignParentRight()
                    sameTop(headLayout)
                    rightMargin = dip(10)
                }
            }.lparams(width = matchParent, height = dip(140))

            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                backgroundColor = Color.WHITE
                minimumHeight = dip(35)

                textView(R.string.shop) {
                    backgroundColor = Color.WHITE
                    compoundDrawablePadding = dip(10)
                    gravity = Gravity.CENTER_VERTICAL
                    textColor = Color.BLACK
                    textSize = 14F
                    setPadding(dip(10), 0, dip(10), 0)
                    setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_my_shop, 0, 0, 0)
                }.lparams(width = wrapContent, height = dip(50)) {
                    topMargin = dip(1)
                }
                textView { }.applyRecursively { text2 }

            }.lparams(width = matchParent, height = dip(50))

            textView(R.string.my_red_packge) {
                backgroundColor = Color.WHITE
                compoundDrawablePadding = dip(10)
                gravity = Gravity.CENTER_VERTICAL
                textColor = Color.BLACK
                textSize = 14F
                setPadding(dip(10), 0, dip(10), 0)
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_red, 0, 0, 0)

            }.lparams(width = matchParent, height = dip(50)) {
                topMargin = dip(1)
            }
            textView(R.string.about) {
                backgroundColor = Color.WHITE
                compoundDrawablePadding = dip(10)
                gravity = Gravity.CENTER_VERTICAL
                textColor = Color.BLACK
                textSize = 14F
                setPadding(dip(10), 0, dip(10), 0)
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_about, 0, R.drawable.arrow_right, 0)

            }.lparams(width = matchParent, height = dip(50)) {
                topMargin = dip(1)
            }
        }
    }.view
}