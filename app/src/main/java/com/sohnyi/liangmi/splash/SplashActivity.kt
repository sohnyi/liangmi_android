package com.sohnyi.liangmi.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sohnyi.liangmi.MainActivity
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wait3SecondToMain()
    }

    private fun wait3SecondToMain() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }

    }


}