package com.sohnyi.liangmi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import android.widget.NumberPicker.OnScrollListener.SCROLL_STATE_IDLE
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sohnyi.liangmi.utils.copyToClipboard
import com.sohnyi.liangmi.utils.setStatusBarMode
import com.sohnyi.liangmi.veiwmodel.PasswordGeneratorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordGeneratorActivity : AppCompatActivity() {

    private lateinit var tvPassword: TextView
    private lateinit var cbUppercase: CheckBox
    private lateinit var cbLowercase: CheckBox
    private lateinit var cbNumbers: CheckBox
    private lateinit var cbSymbols: CheckBox

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
        setContentView(R.layout.activity_password_generate)
        setStatusBarMode(Color.WHITE, true)

        tvPassword = findViewById(R.id.tv_password)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val ivRenew: ImageView = findViewById(R.id.iv_renew)
        val pickerLength: NumberPicker = findViewById(R.id.picker_length)
        cbUppercase = findViewById(R.id.cb_uppercase)
        cbLowercase = findViewById(R.id.cb_lowercase)
        cbNumbers = findViewById(R.id.cb_numbers)
        cbSymbols = findViewById(R.id.cb_symbols)
        val btnCopy: Button = findViewById(R.id.btn_copy)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pickerLength.minValue = MIN_LENGTH
        pickerLength.maxValue = MAX_LENGTH
        pickerLength.value = passwordLength
        pickerLength.wrapSelectorWheel = false
        cbUppercase.isChecked = uppercaseAvailable
        cbLowercase.isChecked = lowercaseAvailable
        cbNumbers.isChecked = numbersAvailable
        cbSymbols.isChecked = symbolsAvailable

        ivRenew.setOnClickListener {
            it.animate().rotationBy(180f).start()
            reNewPassword()
        }

        pickerLength.setOnScrollListener { _, scrollState ->
            if (scrollState == SCROLL_STATE_IDLE && pickerLength.value != passwordLength) {
                passwordLength = pickerLength.value
                reNewPassword()
            }
        }

        cbUppercase.setOnCheckedChangeListener { _, isChecked ->
            uppercaseAvailable = isChecked
            assembleChanged()
        }
        cbLowercase.setOnCheckedChangeListener { _, isChecked ->
            lowercaseAvailable = isChecked
            assembleChanged()
        }
        cbNumbers.setOnCheckedChangeListener { _, isChecked ->
            numbersAvailable = isChecked
            assembleChanged()
        }
        cbSymbols.setOnCheckedChangeListener { _, isChecked ->
            symbolsAvailable = isChecked
            assembleChanged()
        }

        btnCopy.setOnClickListener {
            copyPassword()
        }

        tvPassword.text = passwordGeneratorViewModel.password
    }

    private fun reNewPassword() {
        lifecycleScope.launch(Dispatchers.Main) {
            passwordGeneratorViewModel.getNewPassword(passwordLength, uppercaseAvailable, lowercaseAvailable, numbersAvailable, symbolsAvailable)
            tvPassword.text = passwordGeneratorViewModel.password
        }
    }

    private fun assembleChanged() {
        cbUppercase.isClickable = lowercaseAvailable || numbersAvailable || symbolsAvailable
        cbLowercase.isClickable = uppercaseAvailable || numbersAvailable || symbolsAvailable
        cbNumbers.isClickable = uppercaseAvailable || lowercaseAvailable || symbolsAvailable
        cbSymbols.isClickable = uppercaseAvailable || lowercaseAvailable || numbersAvailable

        reNewPassword()
    }

    private fun copyPassword() {
        copyToClipboard(this, tvPassword.text.toString())
    }
}