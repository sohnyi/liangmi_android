package com.sohnyi.liangmi.splash

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sohnyi.liangmi.MainActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    private val scope = MainScope()

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
        scope.launch(Dispatchers.Main) {
            delay(time)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }


}