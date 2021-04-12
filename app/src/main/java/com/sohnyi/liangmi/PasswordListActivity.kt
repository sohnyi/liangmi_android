package com.sohnyi.liangmi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.adapter.PasswordListAdapter
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.enums.CategoryEnum
import com.sohnyi.liangmi.utils.setStatusBarMode
import kotlinx.android.synthetic.main.activity_main.*

class PasswordListActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_CATEGORY_ID = "category_id"

        fun newIntent(packageContext: Context, categoryId: Int): Intent {
            return Intent(packageContext, PasswordListActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY_ID, categoryId)
            }
        }
    }

    private val mAdapter by lazy {
        PasswordListAdapter {
            onPasswordClick(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_list)

        setStatusBarMode(Color.WHITE, true)



        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val rv: RecyclerView = findViewById(R.id.rv_password)

        setSupportActionBar(toolbar)

        val id = intent.getIntExtra(EXTRA_CATEGORY_ID, 0)
        val title = when(CategoryEnum.values()[id]) {
            CategoryEnum.SOCIAL -> CategoryEnum.SOCIAL.title
            CategoryEnum.FINANCE -> CategoryEnum.FINANCE.title
            CategoryEnum.GAME -> CategoryEnum.GAME.title
            CategoryEnum.ENTERTAINMENT -> CategoryEnum.ENTERTAINMENT.title
            CategoryEnum.EDUCATION -> CategoryEnum.EDUCATION.title
            CategoryEnum.LIFESTYLE -> CategoryEnum.LIFESTYLE.title
            CategoryEnum.OTHER -> CategoryEnum.OTHER.title
        }


        toolbar.title = title
        setSupportActionBar(toolbar)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = mAdapter
    }

    private fun onPasswordClick(password: Password) {
        val intent = PasswordPagerActivity.newIntent(this, password.id)
        startActivity(intent)
    }

}