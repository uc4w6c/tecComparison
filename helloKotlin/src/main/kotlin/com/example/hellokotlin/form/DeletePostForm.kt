package com.example.hellokotlin.form

import com.fasterxml.jackson.annotation.JsonProperty

data class DeletePostForm(
    @JsonProperty("delete_reason")
    val deletedReason: String
)
