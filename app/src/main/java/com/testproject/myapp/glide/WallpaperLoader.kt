package com.testproject.myapp.glide

import android.graphics.drawable.Drawable

object WallpaperLoader {
    private var drawable: Drawable? = null

    fun setWallpaperImage(drawable: Drawable) {
        this.drawable = drawable
    }

    fun getWallpaper(): Drawable {
        return drawable!!
    }
}