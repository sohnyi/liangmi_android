package com.sohnyi.liangmi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.preference.PreferenceManager
import org.apache.commons.codec.digest.DigestUtils

class LoginActivity : AppCompatActivity() {

    private var savedPassword: String? = null
    private var password = ""

    private val etPassword: EditText by lazy {
        findViewById(R.id.et_password)
    }

    private val tvPassword: TextView by lazy {
        findViewById(R.id.tv_password)
    }

    companion object {
        const val EXTRA_PASSWORD = "com.sohnyi.liangmi.LoginActivity.password"
        private const val NUMBER_OF_PASSWORD = 6
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        savedPassword = intent.getStringExtra(EXTRA_PASSWORD)

        val ivClear: ImageView = findViewById(R.id.iv_clear)
        val btnNext: Button = findViewById(R.id.btn_next)


        ivClear.setOnClickListener {
            etPassword.text.clear()
        }

        btnNext.isEnabled = true
        btnNext.setOnClickListener {
            handleInput(etPassword.text.toString())
        }

        etPassword.addTextChangedListener {
            val length: Int = it?.length ?: 0
            if (length > 0) {
                ivClear.visibility = View.VISIBLE
                btnNext.isEnabled = length >= NUMBER_OF_PASSWORD
            } else {
                ivClear.visibility = View.INVISIBLE
            }
        }

        etPassword.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    handleInput(etPassword.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    private fun handleInput(input: String) {
        if (savedPassword.isNullOrEmpty()) {
            if (password.isEmpty()) {
                etPassword.text.clear()
                password = input
                tvPassword.text = getString(R.string.confirm_password)
            } else {
                if (input == password) {
                    val sp =
                        PreferenceManager.getDefaultSharedPreferences(this@LoginActivity)
                    val editor = sp.edit()
                    val pwSHA256 = DigestUtils.sha256Hex(password)
                    editor.putString("password", pwSHA256)
                    if (editor.commit()) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.password_not_match),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            if (DigestUtils.sha256Hex(input) == savedPassword) {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    this@LoginActivity,
                    getString(R.string.wrong_password),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}