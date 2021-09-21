package com.diegocalero.laliga.ui.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.diegocalero.laliga.R

object ImageViewUtils {
    fun loadImageUrl(context: Context?, imageView: ImageView, url: String?) {
        context?.let {
            Glide
                .with(context)
                .load(url)
                .placeholder(R.drawable.ic_badge_placeholder)
                .into(imageView)
        }
    }
}