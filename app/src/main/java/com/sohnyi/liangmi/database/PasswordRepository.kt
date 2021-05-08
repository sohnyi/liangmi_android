package com.sohnyi.liangmi.database

import android.content.Context
import androidx.room.Room
import com.sohnyi.liangmi.entry.Password
import java.lang.IllegalStateException

/**
 * 密码数据库仓库
 * Create by yi on Sat 2021/05/08
 */

private const val DATABASE_NAME = "liangmi-database"
class PasswordRepository private constructor(context: Context) {

    private val database: LiangmiDatebase = Room.databaseBuilder(
        context.applicationContext,
        LiangmiDatebase::class.java,
        DATABASE_NAME
    ).build()

    private val passwordDAO = database.passwordDao()

    suspend fun getPasswords(): List<Password> = passwordDAO.getAllPassword()

    suspend fun getPasswords(categoryId: Int): List<Password> = passwordDAO.getPasswordsByCategory(categoryId)

    suspend fun addPassword(password: Password) = passwordDAO.addPassword(password)

    companion object {
        private var INSTANCE: PasswordRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = PasswordRepository(context)
            }
        }

        fun get(): PasswordRepository {
            return INSTANCE ?:
            throw IllegalStateException("PasswordRepository must be initialized")
        }
    }
}