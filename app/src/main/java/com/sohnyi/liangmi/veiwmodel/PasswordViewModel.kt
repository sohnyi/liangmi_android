package com.sohnyi.liangmi.veiwmodel

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "PasswordViewModel"
class PasswordViewModel : ViewModel() {


    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
    }
}