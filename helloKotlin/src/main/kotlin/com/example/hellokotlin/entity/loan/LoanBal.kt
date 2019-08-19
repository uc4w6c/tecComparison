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

/**
 * 現在の支払残高を取得する関数
 * TODO: あっているか怪しい。要確認
 */
fun balancesMinus(amount: Long): (Long) -> Long {
    var balanceAmount = amount
    return fun (payment: Long) {
        balanceAmount -= payment
    }
}
