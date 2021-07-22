package com.sohnyi.liangmi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.sohnyi.liangmi.databinding.ActivityLoginBinding
import com.sohnyi.liangmi.extensions.sha256Hex
import com.sohnyi.liangmi.utils.SpUtils

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var password = ""

    companion object {
        private const val NUMBER_OF_PASSWORD = 6

        fun start(packageContext: Context) {
            packageContext.startActivity(Intent(packageContext, LoginActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.ivClear.setOnClickListener {
            binding.etPassword.text.clear()
        }

        binding.btnNext.isEnabled = true
        binding.btnNext.setOnClickListener {
            handleInput(binding.etPassword.text.toString())
        }

        binding.etPassword.addTextChangedListener {
            val length: Int = it?.length ?: 0
            if (length > 0) {
                binding.ivClear.visibility = View.VISIBLE
                binding.btnNext.isEnabled = length >= NUMBER_OF_PASSWORD
            } else {
                binding.ivClear.visibility = View.INVISIBLE
            }
        }

        binding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    handleInput(binding.etPassword.text.toString())
                    true
                }
                else -> false
            }
        }
    }

    private fun handleInput(input: String) {
        if (mToken.isEmpty()) {
            if (password.isEmpty()) {
                binding.etPassword.text.clear()
                password = input
                binding.tvPassword.text = getString(R.string.confirm_password)
            } else {
                if (input == password) {
                    val savedPassword = input + SUFFIX
                    SpUtils.savePasSha256(this, savedPassword.sha256Hex)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        getString(R.string.password_not_match),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            if ((input + SUFFIX).sha256Hex == mToken) {
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