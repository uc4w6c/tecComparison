package com.example.hellokotlin.service

import com.example.hellokotlin.entity.TopicEntity
import com.example.hellokotlin.entity.PostEntity
import com.example.hellokotlin.entity.loan.LoanTran
import com.example.hellokotlin.entity.loan.balancesMinus
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

        val fixedRepaymentAmount = LoanTran.calcFixedRepaymentAmount(borrowingAmount, interestRate, repaymentPeriod)

        val mutableLoanList: MutableList<LoanTran> = mutableListOf()
        var repaymentMouthCount = LoanTran.repaymentMouthCount(repaymentPeriod)
        val balanceMinus = balancesMinus(borrowingAmount)
        var balance = borrowingAmount
        for (i in 1..repaymentMouthCount) {
            // TODO: ここの宣言で interestRateが変数として認識されてしまいエラーになっている
            val loanTran = LoanTran.build {
                id = i.toLong()
                month = "1901/01" // 一旦仮
                total = fixedRepaymentAmount
                interestRate = interestRate
                beforeBalance = 0
            }

            mutableLoanList.add(loanTran)
            balance = balanceMinus(loanTran.principal)
        }

        return mutableLoanList
    }
}
