package com.example.hellokotlin.entity.loan

import java.math.RoundingMode
import kotlin.math.floor

/**
 * ローン返済の明細情報
 */
data class LoanTran(
        val id: Long,
        val month: String,
        val total: Long,
        val principal: Long,
        val interest: Long,
        val balance: Long
)


/**
 * 毎月の一定返済金額を計算する
 * 参考: https://ja.wikipedia.org/wiki/元利均等返済
 * package-level関数
 */
fun calcFixedRepaymentAmount(balance:Long,
                             interestRate: Double,
                             repaymentPeriod: Int): Long {

    val repaymentMouthCount = repaymentPeriod / 12
    // 月の金利
    // MEMO: マジックナンバーになっているけど、一旦これで
    val mouthRate = interestRate / 100 / 12
    // TODO: 以下BigDecimal使っているけどあってる？確認
    // 計算分子
    val numerator = (balance.toBigDecimal() * mouthRate.toBigDecimal() * (1 + mouthRate).toBigDecimal().pow(repaymentPeriod)).setScale(10, RoundingMode.DOWN)
    // 計算分母
    val denominator = ((1 + mouthRate).toBigDecimal().pow(repaymentPeriod - 1)).setScale(10, RoundingMode.DOWN)
    // TODO: 以下がエラーになる理由がわからない・・・
    return (numerator.toBigDecimal() / denominator.toBigDecimal()).setScale(0, RoundingMode.DOWN).toLong()
}
