package com.sohnyi.liangmi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.StringBuilder

class LoginActivity: AppCompatActivity() {

    private var passwordSB = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ///TODO handle the different status between has set password and no password has set

        setViewOnClickListener()
    }

    private fun setViewOnClickListener() {
        tv_0.setOnClickListener { onNumberClick(it.id) }
        tv_1.setOnClickListener { onNumberClick(it.id) }
        tv_2.setOnClickListener { onNumberClick(it.id) }
        tv_3.setOnClickListener { onNumberClick(it.id) }
        tv_4.setOnClickListener { onNumberClick(it.id) }
        tv_5.setOnClickListener { onNumberClick(it.id) }
        tv_6.setOnClickListener { onNumberClick(it.id) }
        tv_6.setOnClickListener { onNumberClick(it.id) }
        tv_7.setOnClickListener { onNumberClick(it.id) }
        tv_8.setOnClickListener { onNumberClick(it.id) }
        tv_9.setOnClickListener { onNumberClick(it.id) }

        tv_backspace.setOnClickListener { onBackSpaceClick() }

        tv_enter.setOnClickListener { onEnterClick() }
    }

    private fun onNumberClick(id: Int) {
        if (passwordSB.length < 8) {
            when (id) {
                R.id.tv_0 -> passwordSB.append("0")
                R.id.tv_1 -> passwordSB.append("1")
                R.id.tv_2 -> passwordSB.append("2")
                R.id.tv_3 -> passwordSB.append("3")
                R.id.tv_4 -> passwordSB.append("4")
                R.id.tv_5 -> passwordSB.append("5")
                R.id.tv_6 -> passwordSB.append("6")
                R.id.tv_7 -> passwordSB.append("7")
                R.id.tv_8 -> passwordSB.append("8")
                R.id.tv_9 -> passwordSB.append("9")
            }
            tv_password.text = passwordSB.toString()
        }
    }

    private fun onBackSpaceClick() {
        if (passwordSB.isNotEmpty()) {
            passwordSB.deleteCharAt(passwordSB.length - 1)
            tv_password.text = passwordSB.toString()
        }
    }

    private fun onEnterClick() {
        if (passwordSB.length >= 6) {
            Toast.makeText(this, "password enter", Toast.LENGTH_SHORT).show()
            ///TODO handler after password set successful or password enter right
        } else {
            Toast.makeText(this, "password to short", Toast.LENGTH_SHORT).show()
        }
    }
}