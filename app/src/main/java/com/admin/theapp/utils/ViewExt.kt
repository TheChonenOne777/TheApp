package com.admin.theapp.utils

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.visible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun ViewGroup.inflateView(@LayoutRes layout: Int) = LayoutInflater.from(this.context).inflate(layout, this, false)
