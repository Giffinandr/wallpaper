package com.testproject.myapp.viewmodel.repository

object CategoryRepository {
    private var listCategory = mutableMapOf(
        "Dogs" to "dog",
        "Cats" to "cat",
        "Fish" to "fish",
        "Nature" to "nature",
        "Love" to "love",
        "Sky" to "sky",
        "food" to "food",
        "Flower" to "flower",
        "Beach" to "beach",
        "Money" to "money"
    )

    fun getListCategory(): Map<String, String> {
        return listCategory
    }
}