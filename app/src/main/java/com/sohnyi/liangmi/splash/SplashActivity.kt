package com.sohnyi.liangmi.splash

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sohnyi.liangmi.LoginActivity
import com.sohnyi.liangmi.PasswordLab
import com.sohnyi.liangmi.database.PasswordRepository
import com.sohnyi.liangmi.mToken
import com.sohnyi.liangmi.utils.SpUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "SplashActivity"
private const val DELAY_DURATION = 1000L

class SplashActivity : AppCompatActivity() {


    private var tokenInit = false
    private var passwordInit = false
    private var hasDelayed = false

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
            initPassword()
            initToken()
            lifecycleScope.launch {
                delay(DELAY_DURATION)
                hasDelayed = true
                toLogin()
            }
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private fun initToken() {
        lifecycleScope.launch(Dispatchers.IO) {
            mToken = SpUtils.getPasSha256(this@SplashActivity)
            tokenInit = true
        }
    }

    private fun initPassword() {
        lifecycleScope.launch(Dispatchers.IO) {
            PasswordRepository.initialize(applicationContext)
            Log.d(TAG, "onCreate: ${PasswordLab.allPasswords.size}")
            val passwords = PasswordRepository.get().getPasswords()
            PasswordLab.allPasswords.addAll(passwords)
            passwordInit = true
        }
    }

    private fun toLogin() {
        if (hasDelayed && tokenInit && passwordInit) {
            LoginActivity.start(this@SplashActivity)
            finish()
        }
    }
}