package com.sohnyi.liangmi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.PasswordLab
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.viewholder.PasswordHolder

/**
 * 密码列表适配器
 */
class PasswordListAdapter(
    private val categoryId: Int,
    private val onClick: (password: Password) -> Unit
) : RecyclerView.Adapter<PasswordHolder>() {

    private val passwords: MutableList<Password> = mutableListOf()

    init {
        passwords.addAll(PasswordLab.getPasswords(categoryId))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_password, parent, false)
        return PasswordHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: PasswordHolder, position: Int) {
        holder.bind(passwords[position])
    }

    override fun getItemCount(): Int {
        return passwords.size
    }
}