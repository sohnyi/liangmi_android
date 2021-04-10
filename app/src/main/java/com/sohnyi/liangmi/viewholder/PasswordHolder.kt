package com.sohnyi.liangmi.viewholder

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.entry.Password

class PasswordHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private val cm: ClipboardManager by lazy {
        itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvAccount: TextView = itemView.findViewById(R.id.tv_account)
    private val tvPassword: TextView = itemView.findViewById(R.id.tv_password)
    private val ivVisible: ImageView = itemView.findViewById(R.id.iv_visible)
    private val ivCopy: ImageView = itemView.findViewById(R.id.iv_copy)

    private var visible = false

    fun bind(password: Password) {
        tvTitle.text = password.title
        tvAccount.text = password.account

        ivVisible.setOnClickListener {
            visible = !visible
            if (visible) {
                tvPassword.text = password.password
                ivVisible.setImageDrawable(
                    ContextCompat.getDrawable(
                        ivVisible.context,
                        R.drawable.ic_baseline_visibility_off_24
                    )
                )

            } else {
                tvPassword.text = ivVisible.context.resources.getString(R.string.invisible_password)
                ivVisible.setImageDrawable(
                    ContextCompat.getDrawable(
                        ivVisible.context,
                        R.drawable.ic_baseline_visibility_24
                    )
                )

            }
        }

        ivCopy.setOnClickListener {
            val clip = ClipData.newPlainText(
                ivCopy.context.getString(R.string.app_name),
                password.password
            )
            cm.setPrimaryClip(clip)
            Toast.makeText(it.context, "Copied", Toast.LENGTH_SHORT).show()
        }
    }

}