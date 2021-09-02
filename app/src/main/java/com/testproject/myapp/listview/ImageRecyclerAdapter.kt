package com.testproject.myapp.listview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.testproject.myapp.R
import com.testproject.myapp.SecondFragmentDirections
import com.testproject.myapp.network.ConnectNetwork
import com.testproject.myapp.viewmodel.PixabayImage

class ImageRecyclerAdapter: ListAdapter<PixabayImage, ImageViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_list, parent, false)
        return ImageViewHolder(root)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imagePixabay = getItem(position)
        holder.setUrl(imagePixabay.webformatURL, options())
        holder.itemView.setOnClickListener {
            if (ConnectNetwork.isConnect()) {
                val action =
                    SecondFragmentDirections.actionSecondFragmentToThirdFragment(
                        imagePixabay.fullHDURL,
                        imagePixabay.imageURL)

                it.findNavController().navigate(action)
            } else Toast.makeText(it.context,
                "DISCONNECT\ncheck internet connection",
                Toast.LENGTH_LONG).show()
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<PixabayImage>() {
        override fun areItemsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PixabayImage, newItem: PixabayImage): Boolean {
            return oldItem.hash == newItem.hash
        }
    }

    companion object {
        private val requestOptions = RequestOptions()
            .override(500)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.NONE)

        internal fun options(): RequestOptions {
            return requestOptions
        }
    }

}