package com.sohnyi.liangmi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.viewholder.PasswordHolder

/**
 * 密码列表适配器
 */
class PasswordListAdapter(
    private val onClick: (password: Password) -> Unit
) : ListAdapter<Password, PasswordHolder>(PasswordDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_password, parent, false)
        return PasswordHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: PasswordHolder, position: Int) {
        holder.bind(getItem(position))
    }
}