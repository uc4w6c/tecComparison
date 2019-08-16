package com.example.hellokotlin.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="posts")
data class PostEntity(
    @Id @GeneratedValue
    val id: Long,
    val topicId: Long,
    val name: String,
    val body: String,
    val deletedReason: String,
    val deletedAt: LocalDateTime,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
