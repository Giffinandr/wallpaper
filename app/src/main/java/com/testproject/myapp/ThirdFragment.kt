package com.testproject.myapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.testproject.myapp.glide.GlideImageLoader
import com.testproject.myapp.viewmodel.Model

class ThirdFragment : Fragment() {
    private val model by viewModels<Model>()
    private val args by navArgs<ThirdFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third, container, false)

        val imageView = view.findViewById<ImageView>(R.id.selected_image)
        val button = view.findViewById<Button>(R.id.set_wallpaper_button)
        val indicatorBar = view.findViewById<CircularProgressIndicator>(R.id.indicator_bar)

        val options = RequestOptions()
            .centerInside()
            .priority(Priority.HIGH)
            .diskCacheStrategy(DiskCacheStrategy.NONE)

        val loader = GlideImageLoader(imageView, indicatorBar, true)
            loader.load(args.urlImage, options)

        button.setOnClickListener {
            model.setWallpaper(args.urlWallpaper)
        }

        return view
    }
}