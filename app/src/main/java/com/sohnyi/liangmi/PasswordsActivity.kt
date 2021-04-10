package com.sohnyi.liangmi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.adapter.PasswordsAdapter
import com.sohnyi.liangmi.utils.setStatusBarLightMode
import com.sohnyi.liangmi.utils.setStatusBarMode

class PasswordsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "com.sohnyi.liangmi.PasswordsActivity.title"
    }

    private val mAdapter by lazy {
        PasswordsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passwords_avtivity)

        setStatusBarMode(Color.WHITE, true)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val rv: RecyclerView = findViewById(R.id.rv_password)

        val title = intent.getStringExtra(EXTRA_TITLE)


        toolbar.title = title ?: "未知分类"
        setSupportActionBar(toolbar)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter


    }


}