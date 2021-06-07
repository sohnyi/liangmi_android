package com.sohnyi.liangmi.startup

import android.content.Context
import androidx.startup.Initializer
import com.sohnyi.liangmi.database.PasswordRepository

/**
 *
 * Create by yi on Mon 2021/06/07
 */
class RoomInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        PasswordRepository.initialize(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}