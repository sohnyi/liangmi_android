package com.sohnyi.liangmi

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.sohnyi.liangmi.database.PasswordRepository
import com.sohnyi.liangmi.entry.Password
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "PasswordLab"

object PasswordLab {

    val allPasswords = mutableListOf<Password>()

    init {
        allPasswords.clear()
    }

    fun getPasswordsByCategory(id: Int): List<Password> {
        val passwords: MutableList<Password> = mutableListOf()
        allPasswords.forEach {
            if (it.categoryId == id) {
                passwords.add(it)
            }
        }
        return passwords
    }

    fun getPassword(id: Int): Password? {
        allPasswords.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

    fun addPassword(activity: FragmentActivity, password: Password) {
        activity.lifecycleScope.launch(Dispatchers.IO) {
            PasswordRepository.get().addPassword(password)
        }
        allPasswords.add(password)
    }

    fun updatePassword(activity: FragmentActivity, password: Password) {
        activity.lifecycleScope.launch(Dispatchers.IO) {
            PasswordRepository.get().updatePassword(password)
        }
        val index = allPasswords.indexOfFirst { it.id == password.id }
        if (index != -1) {
            allPasswords[index] = password
        }
    }
}