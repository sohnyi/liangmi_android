package com.sohnyi.liangmi.extensions

import android.content.res.Resources
import android.util.TypedValue

/**
 * 获取一个 dp 密度对应的 px 值
 */
val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    )