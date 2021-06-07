package com.sohnyi.liangmi.entry

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "category_id") val categoryId: Int,
    var title: String,
    var account: String,
    var password: String,
    var remark: String? = null,
    @ColumnInfo(name = "icon_id") var iconId: Int? = null,
    @ColumnInfo(name = "create_time") val crateTime: Long? = null,
    @ColumnInfo(name = "update_time") var updateTime: Long? = null
)
