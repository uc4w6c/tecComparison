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
        val repaymentMouthCount = LoanTran.repaymentMouthCount(repaymentPeriod)
        val balanceMinus = balancesMinus(borrowingAmount)
        var balance = borrowingAmount
        // ループさせるのオブジェクト指向っぽくなくてダサいけど、一旦これで
        for (i in 1..repaymentMouthCount) {
            // TODO: ここの宣言で interestRateが変数として認識されてしまいエラーになっている
            // TODO: 最後のループでは残高を全て支払うように修正
            val loanTran = LoanTran.build {
                // thisをつけないとinterestRateが [Val cannot be reassigned]でエラーになる（同名引数）
                this.id = i.toLong()
                this.month = "1901/01" // 一旦仮
                this.total = fixedRepaymentAmount
                // 以下がだめな場合はこれを試す this@build.interestRate = interestRate
                // 参考: https://discuss.kotlinlang.org/t/problems-with-apply-function/1934
                this.interestRate = interestRate
                this.beforeBalance = balance
            }

            mutableLoanList.add(loanTran)
            balance = balanceMinus(loanTran.principal)
        }

        return mutableLoanList
    }
}
