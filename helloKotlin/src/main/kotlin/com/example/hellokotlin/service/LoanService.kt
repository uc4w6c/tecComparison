package com.example.hellokotlin.service

import com.example.hellokotlin.entity.TopicEntity
import com.example.hellokotlin.entity.PostEntity
import com.example.hellokotlin.entity.loan.LoanTran
import com.example.hellokotlin.repository.PostRepository
import com.example.hellokotlin.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.data.domain.PageRequest



@Service
class LoanService(private val topicRepository: TopicRepository) {

    /**
     * 元利均等ローン計算
     */
    fun calcLoan(borrowingAmount: Long,
                 interestRate: Double,
                 repaymentPeriod: Int): List<LoanTran> {

        val fixedRepaymentAmount = calcFixedRepaymentAmount(borrowingAmount, interestRate, repaymentPeriod)

    }
}
