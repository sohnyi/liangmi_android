package com.sohnyi.liangmi.utils

import android.content.Context

fun dp2px(context: Context, dp: Int): Int {
    return (context.resources.displayMetrics.density * dp + 0.5f).toInt()
}

fun px2dp(context: Context, px: Int): Int {
    return (px / context.resources.displayMetrics.density + 0.5f).toInt()
}