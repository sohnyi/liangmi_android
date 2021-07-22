package com.sohnyi.liangmi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.NumberPicker.OnScrollListener.SCROLL_STATE_IDLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sohnyi.liangmi.databinding.ActivityPasswordGenerateBinding
import com.sohnyi.liangmi.utils.copyToClipboard
import com.sohnyi.liangmi.utils.setStatusBarMode
import com.sohnyi.liangmi.veiwmodel.PasswordGeneratorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordGeneratorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasswordGenerateBinding

    private var passwordLength = DEFAULT_LENGTH
    private var uppercaseAvailable = true
    private var lowercaseAvailable = true
    private var numbersAvailable = true
    private var symbolsAvailable = true

    private val passwordGeneratorViewModel by lazy {
        ViewModelProvider(this).get(PasswordGeneratorViewModel::class.java)
    }

    companion object {
        private const val DEFAULT_LENGTH = 12
        private const val MIN_LENGTH = 4
        private const val MAX_LENGTH = 64

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, PasswordGeneratorActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordGenerateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setStatusBarMode(Color.WHITE, true)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.pickerLength.minValue = MIN_LENGTH
        binding.pickerLength.maxValue = MAX_LENGTH
        binding.pickerLength.value = passwordLength
        binding.pickerLength.wrapSelectorWheel = false
        binding.cbUppercase.isChecked = uppercaseAvailable
        binding.cbLowercase.isChecked = lowercaseAvailable
        binding.cbNumbers.isChecked = numbersAvailable
        binding.cbSymbols.isChecked = symbolsAvailable

        binding.ivRenew.setOnClickListener {
            it.animate().rotationBy(180f).start()
            reNewPassword()
        }

        binding.pickerLength.setOnScrollListener { _, scrollState ->
            if (scrollState == SCROLL_STATE_IDLE && binding.pickerLength.value != passwordLength) {
                passwordLength = binding.pickerLength.value
                reNewPassword()
            }
        }

        binding.cbUppercase.setOnCheckedChangeListener { _, isChecked ->
            uppercaseAvailable = isChecked
            assembleChanged()
        }
        binding.cbLowercase.setOnCheckedChangeListener { _, isChecked ->
            lowercaseAvailable = isChecked
            assembleChanged()
        }
        binding.cbNumbers.setOnCheckedChangeListener { _, isChecked ->
            numbersAvailable = isChecked
            assembleChanged()
        }
        binding.cbSymbols.setOnCheckedChangeListener { _, isChecked ->
            symbolsAvailable = isChecked
            assembleChanged()
        }

        binding.btnCopy.setOnClickListener {
            copyPassword()
        }

        binding.tvPassword.text = passwordGeneratorViewModel.password
    }

    private fun reNewPassword() {
        lifecycleScope.launch(Dispatchers.Main) {
            passwordGeneratorViewModel.getNewPassword(
                passwordLength,
                uppercaseAvailable,
                lowercaseAvailable,
                numbersAvailable,
                symbolsAvailable
            )
            binding.tvPassword.text = passwordGeneratorViewModel.password
        }
    }

    private fun assembleChanged() {
        binding.cbUppercase.isClickable = lowercaseAvailable || numbersAvailable || symbolsAvailable
        binding.cbLowercase.isClickable = uppercaseAvailable || numbersAvailable || symbolsAvailable
        binding.cbNumbers.isClickable = uppercaseAvailable || lowercaseAvailable || symbolsAvailable
        binding.cbSymbols.isClickable = uppercaseAvailable || lowercaseAvailable || numbersAvailable

        reNewPassword()
    }

    private fun copyPassword() {
        copyToClipboard(this, binding.tvPassword.text.toString())
    }
}