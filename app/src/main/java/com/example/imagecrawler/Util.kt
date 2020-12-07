package com.example.imagecrawler

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


fun loadImage(view : View, url: String, imgView : ImageView) {
    Glide.with(view)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .centerCrop()
        .error(R.drawable.ic_error)
        .into(imgView)
}

fun convertUrl(serverId: String,id: String, secret: String) : String {
    val baseUrl =  "https://live.staticflickr.com/"
    return baseUrl + serverId + "/" + id + "_" + secret + ".jpg"
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}