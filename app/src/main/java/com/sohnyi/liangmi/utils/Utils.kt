package com.sohnyi.liangmi.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.sohnyi.liangmi.R

fun copyToClipboard(context: Context, content: String) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(
        context.getString(R.string.app_name),
        content
    )
    clipboardManager.setPrimaryClip(clip)
    Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
}

fun showToast(context: Context, content: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, content, duration).show()
}