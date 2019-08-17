package com.example.hellokotlin.form

import com.fasterxml.jackson.annotation.JsonProperty

data class PostForm(
    @JsonProperty("topic_id")
    val topicId: Long,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("body")
    val body: String
)
