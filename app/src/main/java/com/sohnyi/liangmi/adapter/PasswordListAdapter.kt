package com.sohnyi.liangmi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.viewholder.PasswordHolder

class PasswordListAdapter(private val onClick: (password: Password) -> Unit) : RecyclerView.Adapter<PasswordHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_password, parent, false)
        return PasswordHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: PasswordHolder, position: Int) {
        holder.bind(Password(
            position,
            "T$position",
            if (position % 2 == 0) 1 else 0,
            "A$position",
            "P$position")
        )
    }

    override fun getItemCount(): Int {
        return 10
    }
}