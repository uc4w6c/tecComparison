package com.example.hellokotlin.service

import com.example.hellokotlin.entity.TopicEntity
import com.example.hellokotlin.entity.PostEntity
import com.example.hellokotlin.repository.PostRepository
import com.example.hellokotlin.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.data.domain.PageRequest



@Service
class TopicService(private val topicRepository: TopicRepository,
                   private val postRepository: PostRepository) {

    companion object {
        const val LIMIT_SIZE = 100
    }

    fun findById(id: Long, page: Long): List<PostEntity> {
        val topic = topicRepository.getOne(id)
        return postRepository.findByTopicId(topic.id, PageRequest.of(page.toInt(), LIMIT_SIZE)).getContent()
    }
}
