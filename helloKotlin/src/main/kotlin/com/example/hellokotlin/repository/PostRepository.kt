package com.example.hellokotlin.repository

import com.example.hellokotlin.entity.PostEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PostRepository : JpaRepository<PostEntity, Long> {
    @Query(value = "SELECT p FROM PostEntity p WHERE p.topicId = :topicId")
    fun findByTopicId(@Param("topicId") topicId: Long, page: Pageable): Page<PostEntity>
}
