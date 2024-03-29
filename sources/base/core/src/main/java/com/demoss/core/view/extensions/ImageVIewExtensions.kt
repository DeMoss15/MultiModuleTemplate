package com.demoss.core.view.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.showImage(image: String) {
    Glide.with(context).load(image).into(this)
}

fun ImageView.showImage(image: String, options: RequestOptions) {
    Glide.with(context)
        .load(image)
        .apply(options)
        .into(this@showImage)
}