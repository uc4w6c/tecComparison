package com.example.hellokotlin.repository

import com.example.hellokotlin.entity.TopicEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<TopicEntity, Long> {}
