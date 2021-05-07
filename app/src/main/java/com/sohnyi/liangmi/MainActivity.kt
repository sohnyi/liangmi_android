package com.sohnyi.liangmi

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohnyi.liangmi.adapter.CategoryAdapter
import com.sohnyi.liangmi.common.CommonDividerItemDecoration
import com.sohnyi.liangmi.database.PasswordRepository
import com.sohnyi.liangmi.entry.Category
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.enums.CategoryEnum
import com.sohnyi.liangmi.utils.setStatusBarMode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        setSupportActionBar(toolbar)
        recycler_view.apply {
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
        if (item.itemId ==  R.id.settings) {
            Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun onCategoryClick(category: Category) {
        val intent = PasswordListActivity.newIntent(this, category.id)
        startActivity(intent)
    }
}
