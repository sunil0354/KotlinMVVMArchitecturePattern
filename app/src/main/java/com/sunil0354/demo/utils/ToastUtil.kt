package com.sunil0354.demo.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
    fun showNormalToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}