package com.example.hellokotlin.entity

import java.sql.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="topics")
data class TopicEntity(
    @Id @GeneratedValue
    val id: Long,
    val name: String,
    val createdAt: Date,
    val updatedAt: Date
)
