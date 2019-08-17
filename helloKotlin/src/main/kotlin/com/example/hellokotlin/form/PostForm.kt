package com.example.hellokotlin.form

data class PostForm(
    val topicId: Long,
    val name: String,
    val body: String
)
