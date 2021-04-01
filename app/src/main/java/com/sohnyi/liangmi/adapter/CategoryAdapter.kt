package com.sohnyi.liangmi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.R
import com.sohnyi.liangmi.entry.Category
import com.sohnyi.liangmi.enums.CategoryEnum
import com.sohnyi.liangmi.viewholder.CategoryViewHolder

class CategoryAdapter(private val onClick: (category: Category) -> Unit) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private val categories by lazy {
        arrayOf(
            Category(CategoryEnum.values()[0].id, CategoryEnum.values()[0].name),
            Category(CategoryEnum.values()[1].id, CategoryEnum.values()[1].name),
            Category(CategoryEnum.values()[2].id, CategoryEnum.values()[2].name),
            Category(CategoryEnum.values()[3].id, CategoryEnum.values()[3].name),
            Category(CategoryEnum.values()[4].id, CategoryEnum.values()[4].name),
            Category(CategoryEnum.values()[5].id, CategoryEnum.values()[5].name),
            Category(CategoryEnum.values()[6].id, CategoryEnum.values()[6].name)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}