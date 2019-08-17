package com.example.hellokotlin.service

import com.example.hellokotlin.entity.PostEntity
import com.example.hellokotlin.exception.ValidationException
import com.example.hellokotlin.form.PostForm
import com.example.hellokotlin.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(private val postRepository: PostRepository) {

    /**
     * 投稿をインサートする
     */
    fun insertPost(postForm: PostForm): PostEntity {
        val postEntity = PostEntity(postForm.topicId, postForm.name, postForm.body)
        postRepository.save(postEntity)
        return postEntity
    }

    /**
     * 指定した投稿を削除する
     */
    fun deletePost(id: Long, deletedReason: String) {
        val postEntity = postRepository.findById(id)
                            .orElseThrow {throw ValidationException("エラー")}
        val deletePostEntity = postEntity.copy(deletedReason = deletedReason)
        postRepository.save(deletePostEntity)
    }
}
