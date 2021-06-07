package com.sohnyi.liangmi.viewholder

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.entry.Password
import com.sohnyi.liangmi.enums.Icons
import com.sohnyi.liangmi.utils.copyToClipboard

class PasswordHolder(itemView: View, private val onClick: (password: Password) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)
    private val tvAccount: TextView = itemView.findViewById(R.id.tv_account)
    private val tvPassword: TextView = itemView.findViewById(R.id.tv_password)
    private val ivVisible: ImageView = itemView.findViewById(R.id.iv_visible)
    private val ivCopy: ImageView = itemView.findViewById(R.id.iv_copy)

    private var visible = false

    fun bind(password: Password) {
        tvTitle.text = password.title

        password.iconId?.let {
            val drawableId = when (Icons.values()[it]) {
                Icons.QQ -> R.drawable.ic_qq
                Icons.WECHAT -> R.drawable.ic_wechat
                else -> 0
            }

            if (drawableId != 0) {
                ivIcon.setImageDrawable(ContextCompat.getDrawable(ivIcon.context, drawableId))
            } else {
                ivIcon.setImageDrawable(ColorDrawable(Color.WHITE))
            }
        }



        tvAccount.text = password.account

        itemView.setOnClickListener {
            onClick(password)
        }

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
            copyToClipboard(it.context, password.password)
        }
    }

}