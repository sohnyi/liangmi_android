package com.sohnyi.liangmi

import com.sohnyi.liangmi.entry.Password

object PasswordLab {

    val allPasswords = mutableListOf<Password>()

    fun getPasswords(id: Int): List<Password> {
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

}