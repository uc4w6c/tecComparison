package com.example.hellokotlin.controller

import com.example.hellokotlin.entity.PostEntity
import com.example.hellokotlin.entity.TopicEntity
import com.example.hellokotlin.form.PostForm
import com.example.hellokotlin.service.PostService
import com.example.hellokotlin.service.TopicService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

/**
 * トピック操作API
 */
@RestController
@RequestMapping(path= arrayOf("/post"))
class PostController(private val postService: PostService) {
    @RequestMapping(path= arrayOf("/"), method= arrayOf(RequestMethod.POST))
    fun insertPost(@RequestBody postForm: PostForm): PostEntity {
        return postService.insertPost(postForm)
    }
}
