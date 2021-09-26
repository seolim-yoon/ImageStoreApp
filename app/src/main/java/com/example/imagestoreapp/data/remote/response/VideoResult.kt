package com.example.imagestoreapp.data.remote.response

import com.example.imagestoreapp.ui.model.ThumbnailModel
import com.google.gson.annotations.SerializedName

data class VideoResult(
    @SerializedName("documents")
    val documents: List<Document>,
    @SerializedName("meta")
    val meta: Meta
) {
    data class Document(
        @SerializedName("author")
        val author: String,
        @SerializedName("datetime")
        val datetime: String,
        @SerializedName("play_time")
        val playTime: Int,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    )

    data class Meta(
        @SerializedName("is_end")
        val isEnd: Boolean,
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @SerializedName("total_count")
        val totalCount: Int
    )
}

fun VideoResult.transformThumbnailModel() =
    this.documents.map {
        ThumbnailModel(
            dateTime = it.datetime,
            url = it.thumbnail,
            isVideo = true,
            isStore = false
        )
    }