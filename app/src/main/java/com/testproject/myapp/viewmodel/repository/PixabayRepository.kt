package com.testproject.myapp.viewmodel.repository

import com.testproject.myapp.viewmodel.PixabayImage
import com.testproject.myapp.service.PixabayService
import com.testproject.myapp.viewmodel.KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PixabayRepository @Inject constructor(
    private val pixabayService: PixabayService
) {
    suspend fun getPixabayList(q: String): Flow<List<PixabayImage>> {
        return flow {
            val listPixabay = pixabayService.create().getImageResults(KEY, "$q+background", 60).hits
            this.emit(listPixabay)
        }.flowOn(Dispatchers.IO)
    }

}