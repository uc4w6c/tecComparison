package com.example.hellokotlin.service

import com.example.hellokotlin.entity.TopicEntity
import com.example.hellokotlin.entity.PostEntity
import com.example.hellokotlin.repository.PostRepository
import com.example.hellokotlin.repository.TopicRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(private val topicRepository: TopicRepository,
                   private val postRepository: PostRepository) {
    fun findById(id: Long, page: Pageable): List<PostEntity> {
        val topic = topicRepository.getOne(id)
        return postRepository.findByTopicId(topic.id, page)
    }
}
