package com.sohnyi.liangmi.common

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 * 可设置滚动速度的平滑 Scroller
 * Create by yi on 5/7/21
 */
class SpeedyLinearSmoothScroller(context: Context) : LinearSmoothScroller(context) {

    var duration = 5f

    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        return duration / displayMetrics.densityDpi
    }
}