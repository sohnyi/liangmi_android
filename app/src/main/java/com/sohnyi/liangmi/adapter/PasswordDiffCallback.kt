package com.sohnyi.liangmi.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sohnyi.liangmi.entry.Password

/**
 * Create by yi on Tue 2021/06/08
 */
object PasswordDiffCallback : DiffUtil.ItemCallback<Password>() {
    override fun areItemsTheSame(oldItem: Password, newItem: Password): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Password, newItem: Password): Boolean {
        return oldItem == newItem
    }

}