package com.example.hellokotlin.controller

import com.example.hellokotlin.entity.PostEntity
import com.example.hellokotlin.entity.TopicEntity
import com.example.hellokotlin.service.TopicService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

/**
 * トピック操作API
 */
@RestController
@RequestMapping(path= arrayOf("/topic"))
class TopicController(private val topicService: TopicService) {
    @RequestMapping(path= arrayOf("{id}"), method= arrayOf(RequestMethod.GET))
    fun index(@PathVariable id: Long, page: Pageable): List<PostEntity> {
        return topicService.findById(id, page)
    }
}
