package com.sohnyi.liangmi.veiwmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.sohnyi.liangmi.utils.giveMeAPassword
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "PasswordGeneratorViewModel"

class PasswordGeneratorViewModel : ViewModel() {

    var password: String = giveMeAPassword()
        private set

    suspend fun getNewPassword(
        passwordLength: Int,
        uppercaseAvailable: Boolean,
        lowercaseAvailable: Boolean,
        numbersAvailable: Boolean,
        symbolsAvailable: Boolean
    ): String {
        withContext(Dispatchers.Default) {
            password = giveMeAPassword(
                passwordLength,
                uppercaseAvailable,
                lowercaseAvailable,
                numbersAvailable,
                symbolsAvailable
            )
        }
        return password
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "onCleared: ")
    }
}