package com.testproject.myapp.glide

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.testproject.myapp.network.ConnectNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GlideImageLoader(
    private val imageView: ImageView?,
    private val progressBar: CircularProgressIndicator?,
    private val wallpaperFlag: Boolean = false
) {
    private var drawableImagePixabay: Drawable? = null

    fun load(url: String?, option: RequestOptions?) {
        if (option == null || !ConnectNetwork.isConnect()) return

        onConnecting()

        DispatchingProgressManager.expect(url, object : UIonProgressListener {

            override val granularityPercentage: Float
                get() = 1.0f

            override fun omProgress(bytesRead: Long, expectedLength: Long) {
                progressBar?.setProgress(
                    (100 * bytesRead / expectedLength).toInt(),
                    true
                )
            }
        })

        if (ConnectNetwork.isConnect()) {
            Glide.with(imageView!!.context)
                .asDrawable()
                .load(url)
                .apply(option)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        DispatchingProgressManager.forget(url!!)
                        onFinished()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                            DispatchingProgressManager.forget(url!!)
                        onFinished()
                        return false
                    }
                })
                .into(imageView /*object : CustomTarget<Drawable>(){

                    override fun onLoadCleared(placeholder: Drawable?) {
                       // DispatchingProgressManager.forget(url!!)
                       // onFinished()
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        imageView.setImageDrawable(resource)
                        loadBitmap(resource)
                    }

                }*/)
        } else {
            Toast.makeText(imageView!!.context, "DISCONNECT\ncheck internet connection", Toast.LENGTH_LONG).show()
        }
    }

    private fun onConnecting() {
        if (progressBar != null) progressBar.visibility = View.VISIBLE
    }

    private fun onFinished() {
        progressBar!!.hide()
        imageView!!.visibility = View.VISIBLE
    }

    private fun loadBitmap(resource: Drawable) {
        CoroutineScope(Dispatchers.IO).launch {
            if (wallpaperFlag) WallpaperLoader.setWallpaperImage(resource)
        }

    }

    fun getDrawable(): Drawable? {
        return drawableImagePixabay
    }
}