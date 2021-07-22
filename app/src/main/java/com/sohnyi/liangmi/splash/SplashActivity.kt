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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "SplashActivity"

class SplashActivity : AppCompatActivity() {

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
            lifecycleScope.launch(Dispatchers.Main) {
                initPassword()
                initToken()
                LoginActivity.start(this@SplashActivity)
            }
        }
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    private suspend fun initToken() {
        withContext(Dispatchers.IO) {
            mToken = SpUtils.getPasSha256(this@SplashActivity)
        }
    }

    private suspend fun initPassword() {
        withContext(Dispatchers.IO) {}
        PasswordRepository.initialize(applicationContext)
        Log.d(TAG, "onCreate: ${PasswordLab.allPasswords.size}")
        val passwords = PasswordRepository.get().getPasswords()
        PasswordLab.allPasswords.addAll(passwords)
    }
}