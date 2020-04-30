package com.sohnyi.liangmi.viewholder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.sohnyi.liangmi.utils.dp2px
import kotlinx.android.synthetic.main.item_password.view.*

class CategoryViewHolder(private val context: Context, view: View): RecyclerView.ViewHolder(view),
    View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    var itemPosition = -1

    fun bind(category: String, position: Int) {
        itemView.tv_category.text = category
        itemPosition = position
        val marginLayoutParams = ViewGroup.MarginLayoutParams(itemView.layoutParams)
        if (position % 2 == 0) {
            marginLayoutParams.setMargins(dp2px(context, 16), dp2px(context, 16), dp2px(context, 8), 0)
        } else{
            marginLayoutParams.setMargins(dp2px(context, 8), dp2px(context, 16), dp2px(context, 16), 0)
        }

        val layoutParams = FrameLayout.LayoutParams(marginLayoutParams)
        itemView.layoutParams = layoutParams

    }

    override fun onClick(v: View?) {
        println("Clicked position $itemPosition")
    }
}