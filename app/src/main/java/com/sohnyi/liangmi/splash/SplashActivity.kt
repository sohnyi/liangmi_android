package com.sohnyi.liangmi.splash

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sohnyi.liangmi.LoginActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    @ExperimentalCoroutinesApi
    private val scope = MainScope()

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
            waitToMain()
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun waitToMain() {
        scope.launch(Dispatchers.Main) {
            delay(1500)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }
    }

    @ExperimentalCoroutinesApi
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }


}