package com.testproject.myapp.viewmodel

import com.google.gson.annotations.SerializedName

class PixabayImage(
    val id: String,
   // val pageURL: String,
    //val type: String,
   // val tags: String,
   // val previewURL: String,
   // val previewWidth: Long,
  //  val previewHeight: Long,
    val webformatURL: String,
  //  val webformatWidth: Long,
   // val webformatHeight: Long,
  //  val largeImageURL: String,
  //  val imageWidth: Long,
  //  val imageHeight: Long,
   // val imageSize: Long,
  //  val views: Long,
   // val downloads: Long,
  //  val collections: Long,
   // val likes: Long,
  //  val comments: Long,
  //  @SerializedName("user_id") val userId: Long,
   // val user: String,
    @SerializedName("id_hash")
    val hash: Long,
    val fullHDURL: String,
    val imageURL: String
)