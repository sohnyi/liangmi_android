package com.sohnyi.liangmi.entry

data class Password(
    val title: String,
    val iconId: Int,
    val account: String,
    val password: String,
    val remark: String? = null
)
