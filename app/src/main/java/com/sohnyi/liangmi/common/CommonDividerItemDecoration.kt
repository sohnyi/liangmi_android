package com.sohnyi.liangmi.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.extensions.dp

/**
 * 通用 recyclerview 分割线
 */
class CommonDividerItemDecoration(
    context: Context,
    private val marginHorizontal: Int,
    private val beginIndex: Int = 0,
    private val endIndex: Int = 0,
) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable? = null

    init {
        mDivider = ContextCompat.getDrawable(context, R.drawable.common_line_divider)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft + marginHorizontal.dp.toInt()
        val right = parent.width - parent.paddingRight - marginHorizontal.dp.toInt()

        val childCount = parent.childCount
        if (beginIndex <= childCount) {
            for (i in beginIndex until childCount - 1 - endIndex) {
                val child = parent.getChildAt(i)
                val params =
                    child.layoutParams as RecyclerView.LayoutParams
                val top = child.bottom + params.bottomMargin

                val bottom = top + if(mDivider != null) mDivider!!.intrinsicHeight else 0
                mDivider?.setBounds(left, top, right, bottom)
                mDivider?.draw(c)
            }
        }
    }
}