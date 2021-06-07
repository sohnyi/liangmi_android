package com.sohnyi.liangmi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sohnyi.liangmi.utils.setStatusBarMode

class PasswordPagerActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_PASSWORD_ID = "password_id"

        fun newIntent(packageContext: Context, passwordId: Int?): Intent {
            return Intent(packageContext, PasswordPagerActivity::class.java).apply {
                putExtra(EXTRA_PASSWORD_ID, passwordId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_pager)

        setStatusBarMode(Color.WHITE, true)

        val passwordId = intent.getIntExtra(EXTRA_PASSWORD_ID, 0)

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = PasswordFragment.newInstance(if (passwordId == 0) null else passwordId)
            fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}