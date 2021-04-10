package com.sohnyi.liangmi

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohnyi.liangmi.adapter.CategoryAdapter
import com.sohnyi.liangmi.common.CommonDividerItemDecoration
import com.sohnyi.liangmi.entry.Category
import com.sohnyi.liangmi.utils.setStatusBarMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        this.setStatusBarMode(Color.WHITE, true)
    }

    private fun initView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CategoryAdapter() {
                onCategoryClick(it)
            }
            addItemDecoration(CommonDividerItemDecoration(this@MainActivity, 16, 0))
        }
    }

    private fun onCategoryClick(category: Category) {
        startActivity(Intent(this, PasswordsActivity::class.java).apply {
            putExtra(PasswordsActivity.EXTRA_TITLE, category.name)
        })
    }
}
