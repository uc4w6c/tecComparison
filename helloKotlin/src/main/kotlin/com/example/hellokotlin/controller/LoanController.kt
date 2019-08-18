package com.example.hellokotlin.controller

import com.example.hellokotlin.entity.loan.LoanTran
import com.example.hellokotlin.service.LoanService
import org.springframework.web.bind.annotation.*

/**
 * ローン計算コントローラ
 */
@RestController
@RequestMapping(path= arrayOf("/loan"))
class LoanController(private val loanService: LoanService) {
    companion object {
        // 借入金額
        const val BORROWING_AMOUNT = 35_000_000L
        // 金利（百分率%表示） NOTE: 計算時は100で割って計算が必要
        const val INTEREST_RATE = 0.5
        // 返済期間（年）
        const val REPAYMENT_PERIOD = 35
    }

    /**
     * 元利均等での住宅ローン返済シミュレーション
     * ボーナス計算はしない
     * 参考: https://keisan.casio.jp/exec/system/1256183644https://keisan.casio.jp/exec/system/1256183644
     */
    @RequestMapping(path= arrayOf("/"), method= arrayOf(RequestMethod.GET))
    fun index(): List<LoanTran> {
        return loanService.calcLoan(BORROWING_AMOUNT, INTEREST_RATE, REPAYMENT_PERIOD)
        // return topicService.findById(id, page)
    }
}
