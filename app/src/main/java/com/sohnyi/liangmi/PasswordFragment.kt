package com.sohnyi.liangmi

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sohnyi.liangmi.database.PasswordRepository
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class PasswordFragment : Fragment() {

    private var mCategoryId: Int? = null
    private var mPassword: Password? = null

    private var mEtTitle: EditText? = null
    private var mEtAccount: EditText? = null
    private var mEtPassword: EditText? = null
    private var mEtRemark: EditText? = null

    companion object {
        private const val ARG_CATEGORY_ID = "category_id"
        private const val ARG_PASSWORD_ID = "password_id"

        fun newInstance(categoryId: Int, passwordId: Int?): Fragment {
            val fragment = PasswordFragment()
            val args = Bundle()
            args.putInt(ARG_CATEGORY_ID, categoryId)
            passwordId?.let {
                args.putInt(ARG_PASSWORD_ID, passwordId)
            }
            return fragment.apply {
                arguments = args
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mCategoryId = arguments?.getInt(ARG_CATEGORY_ID, -1)
        val passwordId: Int? = arguments?.getInt(ARG_PASSWORD_ID)

        if (mCategoryId == null || mCategoryId == -1) {
            showToast(requireActivity().applicationContext, "unknown error")
            requireActivity().supportFragmentManager.popBackStack()
        }

        passwordId?.let {
            mPassword = PasswordLab.getPassword(passwordId)
        }

        setHasOptionsMenu(true)
        ViewModelProvider(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_password, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)

        mEtTitle = view.findViewById(R.id.et_title)
        mEtAccount = view.findViewById(R.id.et_account)
        mEtPassword = view.findViewById(R.id.et_password)
        mEtRemark = view.findViewById(R.id.et_remark)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.new_password)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPassword?.let { password ->

            mEtTitle?.setText(password.title)
            mEtAccount?.setText(password.account)
            mEtPassword?.setText(password.password)
            mEtRemark?.setText(password.remark)

            val updateTimeTv: TextView = view.findViewById(R.id.tv_update_time)
            updateTimeTv.visibility = View.VISIBLE
            updateTimeTv.text =
                SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss",
                    Locale.getDefault()
                ).format(password.crateTime)
        }

        val btnSave: Button = view.findViewById(R.id.btn_save)
        btnSave.setOnClickListener {
            savePassword()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_password, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.give_me_a_password) {
            startActivity(PasswordGeneratorActivity.newIntent(requireActivity()))
        }
        return true
    }

    private fun isInputValid(): Boolean {
        if (mEtTitle?.text.isNullOrBlank()) {
            showToast(requireActivity().applicationContext, "title can not empty")
            return false
        }

        if (mEtAccount?.text.isNullOrBlank()) {
            showToast(requireActivity().applicationContext, "account can not empty")
            return false
        }

        if (mEtPassword?.text.isNullOrBlank()) {
            showToast(requireActivity().applicationContext, "password can not empty")
            return false
        }
        return true
    }

    /**
     * 保存密码
     */
    private fun savePassword() {
        if (!isInputValid()) {
            return
        }

        if (mPassword === null) {
            mPassword = Password(
                categoryId = mCategoryId!!,
                title = mEtTitle?.text.toString(),
                account = mEtAccount?.text.toString(),
                password = mEtPassword?.text.toString(),
                remark = mEtRemark?.text.toString(),
                crateTime = System.currentTimeMillis()
            )
        } else {
            mPassword.apply {
                this?.title = mEtTitle?.text.toString()
                this?.account = mEtAccount?.text.toString()
                this?.password = mEtPassword?.text.toString()
                this?.remark = mEtRemark?.text.toString()
            }
        }

        mPassword?.let {
            lifecycleScope.launch(Dispatchers.IO) {
                PasswordRepository.get().addPassword(it)
                withContext(Dispatchers.Main) {
                    showToast(requireActivity().applicationContext, "saved")
                    requireActivity().finish()
                }
            }
        }
    }
}