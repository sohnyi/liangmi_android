package com.sohnyi.liangmi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sohnyi.liangmi.databinding.ActivityPasswordPagerBinding
import com.sohnyi.liangmi.utils.setStatusBarMode

class PasswordPagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordPagerBinding

    companion object {

        private const val EXTRA_CATEGORY_ID = "category"
        private const val EXTRA_PASSWORD_ID = "password_id"

        fun newIntent(packageContext: Context, categoryId: Int, passwordId: Int?): Intent {
            return Intent(packageContext, PasswordPagerActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY_ID, categoryId)
                putExtra(EXTRA_PASSWORD_ID, passwordId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordPagerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setStatusBarMode(Color.WHITE, true)

        val categoryId: Int = intent.getIntExtra(EXTRA_CATEGORY_ID, -1)
        var passwordId: Int? = intent.getIntExtra(EXTRA_PASSWORD_ID, -1)
        if (passwordId == -1) {
            passwordId = null
        }

        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            fragment = PasswordFragment.newInstance(categoryId, passwordId)
            fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }
}