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
import java.util.*


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
        val calendar: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN);

        // ループさせるのオブジェクト指向っぽくなくてダサいけど、一旦これで
        for (i in 1..repaymentMouthCount) {
            val loanTran = LoanTran.build {
                // thisをつけないとinterestRateが [Val cannot be reassigned]でエラーになる（同名引数）
                this.id = i.toLong()
                this.month = calendar.get(Calendar.YEAR).toString() + "/" + calendar.get(Calendar.MONTH).toString()
                this.total = fixedRepaymentAmount
                this.interestRate = interestRate
                this.beforeBalance = balance
            }

            calendar.add(Calendar.MONTH, 1);
            mutableLoanList.add(loanTran)
            balance = balanceMinus(loanTran.principal)
        }

        return mutableLoanList
    }
}
