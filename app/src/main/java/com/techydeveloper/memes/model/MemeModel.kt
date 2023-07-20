package com.techydeveloper.memes.model

data class MemeModel(
    val author: String? = null,
    val nsfw: Boolean?= null,
    val postLink: String?= null,
    val preview: List<String?>?= null,
    val spoiler: Boolean?= null,
    val subreddit: String?= null,
    val title: String?= null,
    val ups: Int?= null,
    val url: String?= null
)