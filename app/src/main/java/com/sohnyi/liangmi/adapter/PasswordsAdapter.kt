package com.sohnyi.liangmi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.viewholder.PasswordHolder

class PasswordsAdapter : RecyclerView.Adapter<PasswordHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_password, parent, false)
        return PasswordHolder(itemView)
    }

    override fun onBindViewHolder(holder: PasswordHolder, position: Int) {
        holder.bind(Password("T$position", "A$position", "P$position"))
    }

    override fun getItemCount(): Int {
        return 10
    }
}