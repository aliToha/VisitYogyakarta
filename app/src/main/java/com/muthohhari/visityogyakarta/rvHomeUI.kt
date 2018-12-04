package com.muthohhari.visityogyakarta

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class rvHomeUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        cardView {
            lparams(matchParent, wrapContent) {
                margin = dip(15)
                marginEnd = dip(5)
                marginStart = dip(5)
            }
            linearLayout {
                orientation = LinearLayout.VERTICAL
                padding = dip(8)
                lparams(matchParent, wrapContent) {
                    gravity = Gravity.START
                }
                textView {
                    id = R.id.name_place
                    typeface = Typeface.DEFAULT_BOLD
                    textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                    textColor = ContextCompat.getColor(ctx, R.color.dark_grey)
                }.lparams(matchParent, wrapContent) {
                    gravity = Gravity.START
                }
                textView {
                    id = R.id.location
                    textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                    textColor = ContextCompat.getColor(ctx, R.color.dark_grey)
                    topPadding = dip(10)
                    bottomPadding = dip(10)
                }.lparams(matchParent, wrapContent) {
                    gravity = Gravity.START

                }
                imageView {
                    id = R.id.image
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    bottomPadding = dip(10)
                }.lparams(matchParent, 600) {

                }

            }
        }
    }
}