package com.example.hellokotlin.service

import com.example.hellokotlin.entity.TopicEntity
import com.example.hellokotlin.repository.TopicRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val topicRepository: TopicRepository) {
    fun findById(id: Long): TopicEntity {
        return topicRepository.getOne(id)
    }
}
