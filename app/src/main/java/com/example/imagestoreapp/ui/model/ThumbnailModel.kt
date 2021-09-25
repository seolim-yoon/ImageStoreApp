package com.example.imagestoreapp.ui.model

data class ThumbnailModel(
    val dateTime: String,
    val url: String,
    val isVideo: Boolean,
    var isStore: Boolean
)