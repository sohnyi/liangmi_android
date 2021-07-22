package com.sohnyi.liangmi

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohnyi.liangmi.adapter.CategoryAdapter
import com.sohnyi.liangmi.common.CommonDividerItemDecoration
import com.sohnyi.liangmi.databinding.ActivityMainBinding
import com.sohnyi.liangmi.entry.Category
import com.sohnyi.liangmi.utils.setStatusBarMode

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        this.setStatusBarMode(Color.WHITE, true)
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CategoryAdapter {
                onCategoryClick(it)
            }
            addItemDecoration(CommonDividerItemDecoration(this@MainActivity, 16, 0))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings) {
            Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun onCategoryClick(category: Category) {
        val intent = PasswordListActivity.newIntent(this, category.id)
        startActivity(intent)
    }
}
