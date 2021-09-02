package com.testproject.myapp.glide

interface UIonProgressListener {

    val granularityPercentage: Float

    fun omProgress(bytesRead: Long, expectedLength: Long)
}