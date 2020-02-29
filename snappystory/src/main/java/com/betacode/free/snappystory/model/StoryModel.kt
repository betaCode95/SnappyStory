package com.betacode.free.snappystory.model

data class StoryModel(
    val image: Int = 0,
    val time: Int = 0,
    val comments: ArrayList<Comment> = arrayListOf()
)

data class Comment(val header: String = "", val comment: String = "")