package com.sohnyi.liangmi.viewholder

import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.entry.Category
import com.sohnyi.liangmi.enums.CategoryEnum
import com.sohnyi.liangmi.extensions.dp

class CategoryViewHolder(view: View, private val onClick: (category: Category) -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val tvCategory: TextView = itemView.findViewById(R.id.tv_category)
    private val ivCategory: ImageView = itemView.findViewById(R.id.iv_category)

    fun bind(category: Category) {
        tvCategory.text = category.name
        bindItemIcon(category)
        itemView.setOnClickListener { onClick(category) }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            itemView.elevation = 8.dp
            itemView.outlineAmbientShadowColor = Color.DKGRAY
        }
    }

    private fun bindItemIcon(category: Category) {
        val drawableId =
            when (CategoryEnum.values()[category.id]) {
                CategoryEnum.SOCIAL -> R.drawable.ic_social
                CategoryEnum.FINANCE -> R.drawable.ic_finance
                CategoryEnum.EDUCATION -> R.drawable.ic_education
                CategoryEnum.ENTERTAINMENT -> R.drawable.ic_entertainment
                CategoryEnum.GAME -> R.drawable.ic_game
                CategoryEnum.LIFESTYLE -> R.drawable.ic_lifestyle
                CategoryEnum.WORK_OR_STUDY -> 0
                CategoryEnum.PRODUCTIVITY -> 0
                CategoryEnum.UTILITIES -> 0
                CategoryEnum.OTHER -> R.drawable.ic_other
            }
        if (drawableId != 0) {
            ivCategory.setImageDrawable(ContextCompat.getDrawable(ivCategory.context, drawableId))
        }
    }
}