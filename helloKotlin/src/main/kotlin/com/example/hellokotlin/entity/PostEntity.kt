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
        var id: Long?,
        var topicId: Long?,
        var name: String?,
        var body: String?,
        var deletedReason: String?,
        var deletedAt: LocalDateTime?,
        var createdAt: LocalDateTime?,
        var updatedAt: LocalDateTime?
) {
    constructor(topicId: Long, name: String, body: String): this(null, topicId, name, body, null, null, null, null)
}
