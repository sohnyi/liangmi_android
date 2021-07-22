package com.sohnyi.liangmi

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sohnyi.liangmi.databinding.FragmentPasswordBinding
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.utils.showToast
import java.text.SimpleDateFormat
import java.util.*

class PasswordFragment : Fragment() {

    private var _binding: FragmentPasswordBinding? = null

    private val binding get() = _binding!!

    private var mCategoryId: Int? = null
    private var mPassword: Password? = null

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
    ): View {

        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.new_password)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mPassword?.let { password ->

            binding.etTitle.setText(password.title, TextView.BufferType.EDITABLE)
            binding.etAccount.setText(password.account, TextView.BufferType.EDITABLE)
            binding.etPassword.setText(password.password, TextView.BufferType.EDITABLE)
            binding.etRemark.setText(password.remark, TextView.BufferType.EDITABLE)

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
        if (binding.etTitle.text.isNullOrBlank()) {
            showToast(requireActivity().applicationContext, "title can not empty")
            return false
        }

        if (binding.etAccount.text.isNullOrBlank()) {
            showToast(requireActivity().applicationContext, "account can not empty")
            return false
        }

        if (binding.etPassword.text.isNullOrBlank()) {
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

        if (mPassword == null) {
            val password = Password(
                categoryId = mCategoryId!!,
                title = binding.etTitle.text.toString(),
                account = binding.etAccount.text.toString(),
                password = binding.etPassword.text.toString(),
                remark = binding.etRemark.text.toString(),
                crateTime = System.currentTimeMillis()
            )
            PasswordLab.addPassword(requireActivity(), password)
        } else {
            mPassword?.run {
                this.title = binding.etTitle.text.toString()
                this.account = binding.etAccount.text.toString()
                this.password = binding.etPassword.text.toString()
                this.remark = binding.etRemark.text.toString()
                PasswordLab.updatePassword(requireActivity(), this)
            }
        }
        activity?.setResult(Activity.RESULT_OK)
        activity?.finish()

    }
}