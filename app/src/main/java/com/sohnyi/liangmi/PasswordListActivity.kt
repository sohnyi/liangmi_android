package com.sohnyi.liangmi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sohnyi.liangmi.adapter.PasswordListAdapter
import com.sohnyi.liangmi.common.SpeedyLinearSmoothScroller
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.enums.CategoryEnum
import com.sohnyi.liangmi.utils.setStatusBarMode
import kotlin.math.max

private const val REQUEST_CODE_PASSWORD_UPDATE = 1
private const val REQUEST_CODE_PASSWORD_ADD = 2

class PasswordListActivity : AppCompatActivity() {

    private var categoryId: Int = CategoryEnum.EDUCATION.id

    companion object {
        private const val EXTRA_CATEGORY_ID = "category_id"

        fun newIntent(packageContext: Context, categoryId: Int): Intent {
            return Intent(packageContext, PasswordListActivity::class.java).apply {
                putExtra(EXTRA_CATEGORY_ID, categoryId)
            }
        }
    }

    private val scroller by lazy {
        SpeedyLinearSmoothScroller(this)
    }

    private val mAdapter by lazy {
        PasswordListAdapter() {
            onPasswordClick(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_list)

        setStatusBarMode(Color.WHITE, true)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val rv: RecyclerView = findViewById(R.id.rv_password)
        val fabAdd: FloatingActionButton = findViewById(R.id.fab_add)

        setSupportActionBar(toolbar)

        categoryId = intent.getIntExtra(EXTRA_CATEGORY_ID, 0)
        val title = when (CategoryEnum.values()[categoryId]) {
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


        val llm = LinearLayoutManager(this)
        rv.layoutManager = llm
        rv.adapter = mAdapter

        updateList()

        val ivScrollTop: ImageButton = findViewById(R.id.ib_to_top)
        ivScrollTop.setOnClickListener {
            scroller.duration = 5f / (max(llm.findLastVisibleItemPosition(), 15) / 15)
            scroller.targetPosition = 0
            llm.startSmoothScroll(scroller)
        }

        fabAdd.setOnClickListener {
            onAddClick()
        }
    }

    private fun updateList() {
        mAdapter.submitList(PasswordLab.getPasswordsByCategory(categoryId))
    }

    private fun onPasswordClick(password: Password) {
        val intent = PasswordPagerActivity.newIntent(this, categoryId, password.id)
        startActivityForResult(intent, REQUEST_CODE_PASSWORD_UPDATE)
    }

    /**
     * 添加密码点击事件
     */
    private fun onAddClick() {
        startActivityForResult(
            PasswordPagerActivity.newIntent(this, categoryId, null),
            REQUEST_CODE_PASSWORD_ADD
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode) {
            REQUEST_CODE_PASSWORD_UPDATE -> updateList()
            REQUEST_CODE_PASSWORD_ADD -> updateList()
        }
    }

}