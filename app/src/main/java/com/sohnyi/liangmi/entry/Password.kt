package com.sohnyi.liangmi.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    @PrimaryKey val id: Int,
    val title: String,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    @ColumnInfo(name = "icon_id") val iconId: Int,
    val account: String,
    val password: String,
    val remark: String? = null
)
