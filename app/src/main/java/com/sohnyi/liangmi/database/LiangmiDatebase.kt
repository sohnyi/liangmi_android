package com.sohnyi.liangmi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sohnyi.liangmi.entry.Password

/**
 * 应用数据库
 * Create by yi on Fri 2021/05/07
 */
private const val DATABASE_VERSION = 1
@Database(entities = [Password::class], version = DATABASE_VERSION)
abstract class LiangmiDatebase : RoomDatabase() {

    abstract fun passwordDao(): PasswordDAO
}