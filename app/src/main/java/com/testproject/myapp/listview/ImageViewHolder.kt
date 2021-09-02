package com.testproject.myapp.listview

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.testproject.myapp.R
import com.testproject.myapp.glide.GlideImageLoader

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView: ImageView = itemView.findViewById(R.id.image_view_item)
    private val indicator: CircularProgressIndicator = itemView.findViewById(R.id.circle_indicator)

    fun setUrl(urlImage: String, options: RequestOptions) {
        GlideImageLoader(imageView, indicator).load(urlImage, options)
    }

}