package com.sohnyi.liangmi.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_password.view.*

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view),
    View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    var itemPosition = -1

    fun bind(category: String, position: Int) {
        itemView.tv_category.text = category
        itemPosition = position
    }

    override fun onClick(v: View?) {
        println("Clicked position $itemPosition")
    }
}