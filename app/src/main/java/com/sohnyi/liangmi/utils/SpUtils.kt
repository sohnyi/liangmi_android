package com.sohnyi.liangmi.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object SpUtils {

    private const val KEY_PASSWORD = "password"

    private var sp: SharedPreferences? = null

    fun savePasSha256(context: Context, password: String) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(context)
        }
        val editor = sp?.edit()
        editor?.putString(KEY_PASSWORD, password)
        editor?.apply()
    }

    fun getPasSha256(context: Context): String {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(context)
        }
        return sp?.getString(KEY_PASSWORD, "") ?: ""
    }
}