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
) {
    class Builder {
        var id: Long = 0
        var month: String = "1901/01"
        var total: Long = 0
        var interestRate: Double = 0.0
        var beforeBalance: Long = 0

        fun build(): LoanTran {
            val interest = calcMonthInterest(beforeBalance, interestRate)
            return LoanTran(
                    id = id,
                    month = month,
                    total = total,
                    principal = total - interest,
                    interest = interest,
                    balance = beforeBalance - total
            )
        }
    }

    companion object {
        fun build(f: Builder.() -> Unit): LoanTran {
            val builder = Builder()
            builder.f()
            return builder.build()
        }

        /**
         * 年数から支払回数を計算
         */
        fun repaymentMouthCount(repaymentPeriod: Int): Int {
            return repaymentPeriod * 12
        }

        /**
         * 年利から月ごとの金利を計算
         * MEMO: Package-levelでこんなに関数書いていいいのか怪しい。
         *       一応変えたけど・・・
         */
        fun mouthRate(interestRate: Double): Double {
            return (interestRate.toBigDecimal() / 100.toBigDecimal() / 12.toBigDecimal())
                    .setScale(10, RoundingMode.DOWN).toDouble()
        }

        /**
         * 現在の残高と年利から今月の支払い利息を計算
         */
        fun calcMonthInterest(balance: Long, interestRate: Double): Long {
            return (balance.toBigDecimal() * mouthRate(interestRate).toBigDecimal())
                        .setScale(10, RoundingMode.DOWN).toLong()
        }

        /**
         * 毎月の一定返済金額を計算する
         * 参考: https://ja.wikipedia.org/wiki/元利均等返済
         * package-level関数
         */
        fun calcFixedRepaymentAmount(balance:Long,
                                     interestRate: Double,
                                     repaymentPeriod: Int): Long {

            val repaymentMouthCount = repaymentMouthCount(repaymentPeriod)
            // 月の金利
            // MEMO: マジックナンバーになっているけど、一旦これで
            val mouthRate = mouthRate(interestRate)
            // TODO: 以下BigDecimal使っているけどあってる？確認
            // 計算分子
            val numerator = (balance.toBigDecimal() * mouthRate.toBigDecimal() * (1 + mouthRate).toBigDecimal().pow(repaymentPeriod)).setScale(10, RoundingMode.DOWN)
            // 計算分母
            val denominator = ((1 + mouthRate).toBigDecimal().pow(repaymentPeriod - 1)).setScale(10, RoundingMode.DOWN)

            return  (numerator / denominator).setScale(0, RoundingMode.DOWN).toLong()
        }
    }
}


