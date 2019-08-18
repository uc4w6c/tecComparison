package com.example.hellokotlin.entity.loan

/**
 * ローン残高
 */
data class LoanBal(
        // 残りの残高
        val amount: Long,
        // 残りの支払回数
        val repaymentCount: Long
)

