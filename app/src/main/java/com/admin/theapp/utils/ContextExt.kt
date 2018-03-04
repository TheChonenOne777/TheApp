package com.admin.theapp.utils

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

fun Context.toast(@StringRes text: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}
