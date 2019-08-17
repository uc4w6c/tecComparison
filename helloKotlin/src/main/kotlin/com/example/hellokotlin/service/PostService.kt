package com.example.hellokotlin.service

import com.example.hellokotlin.entity.PostEntity
import com.example.hellokotlin.entity.TopicEntity
import com.example.hellokotlin.form.PostForm
import com.example.hellokotlin.repository.PostRepository
import com.example.hellokotlin.repository.TopicRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

    fun insertPost(postForm: PostForm): PostEntity {
        var postEntity = PostEntity(postForm.topicId, postForm.name, postForm.body)
        postRepository.save(postEntity)
        return postEntity
    }
}
