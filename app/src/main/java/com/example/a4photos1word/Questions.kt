package com.example.a4photos1word

import androidx.annotation.DrawableRes

data class Questions (
    var id: Int,
    @DrawableRes val images: List<Int>,
    var answer: String
)