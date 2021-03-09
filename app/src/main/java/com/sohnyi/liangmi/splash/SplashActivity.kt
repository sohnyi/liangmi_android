package com.sohnyi.liangmi.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import com.sohnyi.liangmi.LoginActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private var savedPassword: String? = null

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
            waitToMain()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            setSavedPassword()
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun waitToMain() {
        lifecycleScope.launch(Dispatchers.Default) {
            delay(1500)
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java).apply {
                putExtra(LoginActivity.EXTRA_PASSWORD, savedPassword)
            })
            finish()
        }
    }

    private fun setSavedPassword() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this@SplashActivity)
        savedPassword = sp.getString("password", "")
    }
}