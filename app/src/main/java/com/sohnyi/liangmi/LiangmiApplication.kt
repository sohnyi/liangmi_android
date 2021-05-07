package com.sohnyi.liangmi

import android.app.Application
import com.sohnyi.liangmi.database.PasswordRepository

/**
 *
 * Create by yi on Sat 2021/05/08
 */
class LiangmiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PasswordRepository.initialize(this)
    }
}