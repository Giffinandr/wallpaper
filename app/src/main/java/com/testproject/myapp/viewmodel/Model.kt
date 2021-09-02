package com.testproject.myapp.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.testproject.myapp.network.ConnectNetwork
import com.testproject.myapp.viewmodel.repository.PixabayRepository
import com.testproject.myapp.service.PixabayService
import com.testproject.myapp.viewmodel.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

const val KEY = "22685638-e4be928bf4a48ef35f103aebc"

class Model(application: Application) : AndroidViewModel(application){
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private val category = CategoryRepository
    private val service = PixabayService()
    private val repository = PixabayRepository(service)
    private val list = MutableLiveData<List<PixabayImage>>()
    private val listCategory = MutableLiveData<Map<String, String>>()
    private val wallpaperManager:WallpaperManager = WallpaperManager.getInstance(context)

    init {
        ConnectNetwork.create(context)
        viewModelScope.launch {
            listCategory.value = category.getListCategory()
        }
    }

    fun setPixabayList(q: String) {
        viewModelScope.launch {
            repository.getPixabayList(q).collect {
                list.value = it
            }
        }
    }

    fun setWallpaper(url: String) {

        Glide.with(context)
            .asBitmap()
            .centerInside()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    viewModelScope.launch(Dispatchers.IO) {
                        wallpaperManager.setBitmap(resource)
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    fun getList(): LiveData<List<PixabayImage>> {
        return list
    }

    fun getListCategory(): MutableLiveData<Map<String, String>> {
        return listCategory
    }

    fun isConnect(): Boolean {
        return ConnectNetwork.isConnect()
    }
}