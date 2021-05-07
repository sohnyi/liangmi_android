package com.sohnyi.liangmi.database

import androidx.room.*
import com.sohnyi.liangmi.entry.Password

/**
 *
 * Create by yi on 5/6/21
 */
@Dao
interface PasswordDAO {

    @Query("SELECT * FROM password")
    suspend fun getAllPassword(): List<Password>

    @Query("SELECT * FROM password WHERE category_id LIKE :categoryId")
    suspend fun getPasswordsByCategory(categoryId: Int): List<Password>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPassword(password: Password)

    @Delete
    suspend fun deletePassword(password: Password)

    @Update
    suspend fun updatePassword(password: Password)
}