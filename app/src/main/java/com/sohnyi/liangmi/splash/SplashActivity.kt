package com.sohnyi.liangmi.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sohnyi.liangmi.MainActivity
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class SplashActivity: AppCompatActivity() {

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
            waitToMain(1000)
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun waitToMain(time: Long = 3000) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Default) {
                delay(time)
            }
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }
}