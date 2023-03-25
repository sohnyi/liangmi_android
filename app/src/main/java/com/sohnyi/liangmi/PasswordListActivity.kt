package com.sohnyi.liangmi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohnyi.liangmi.adapter.PasswordListAdapter
import com.sohnyi.liangmi.common.SpeedyLinearSmoothScroller
import com.sohnyi.liangmi.databinding.ActivityPasswordListBinding
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.enums.CategoryEnum
import com.sohnyi.liangmi.utils.setStatusBarMode
import kotlin.math.max

private const val REQUEST_CODE_PASSWORD_UPDATE = 1
private const val REQUEST_CODE_PASSWORD_ADD = 2

class PasswordListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordListBinding

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
        binding = ActivityPasswordListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setStatusBarMode(Color.WHITE, true)

        categoryId = intent.getIntExtra(EXTRA_CATEGORY_ID, 0)
        val title = when (CategoryEnum.values()[categoryId]) {
            CategoryEnum.SOCIAL -> getString(R.string.social)
            CategoryEnum.FINANCE -> getString(R.string.finance)
            CategoryEnum.GAME -> getString(R.string.game)
            CategoryEnum.ENTERTAINMENT -> getString(R.string.entertainment)
            CategoryEnum.EDUCATION -> getString(R.string.education)
            CategoryEnum.LIFESTYLE -> getString(R.string.lifestyle)
            CategoryEnum.WORK_OR_STUDY -> getString(R.string.work_or_study)
            CategoryEnum.PRODUCTIVITY -> getString(R.string.productivity)
            CategoryEnum.UTILITIES -> getString(R.string.utilities)
            else -> getString(R.string.other)
        }


        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        val llm = LinearLayoutManager(this)
        binding.rvPassword.layoutManager = llm
        binding.rvPassword.adapter = mAdapter

        updateList()

        val ivScrollTop: ImageButton = findViewById(R.id.ib_to_top)
        ivScrollTop.setOnClickListener {
            scroller.duration = 5f / (max(llm.findLastVisibleItemPosition(), 15) / 15)
            scroller.targetPosition = 0
            llm.startSmoothScroll(scroller)
        }

        binding.fabAdd.setOnClickListener {
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

    @Deprecated("Deprecated in Java")
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