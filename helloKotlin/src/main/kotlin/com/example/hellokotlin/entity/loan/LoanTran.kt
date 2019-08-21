package com.example.hellokotlin.entity.loan

import java.math.BigDecimal
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
            val principal = total - interest
            // 支払後残高が今回支払額より少ない場合は最終支払分と判断しまとめる
            val lastBalance = if (beforeBalance - principal - principal < 0) beforeBalance - principal else 0

            return LoanTran(
                    id = id,
                    month = month,
                    total = principal + lastBalance,
                    principal = principal + lastBalance,
                    interest = interest,
                    balance = beforeBalance - principal - lastBalance
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
            // TODO: 0.005が出力してほしいが、0が出力されてしまう
            //  Doubleから BigDecimalはvalueOfを利用した方がいいらしいが、、、
            //  普通にdivideを使わないといけない？？
            //  この記事だといけそうだけど・・https://qiita.com/namidame_Ba/items/55becce93cfae76dce19
            //  多分 - が勝手に0桁roundになる
            return (BigDecimal.valueOf(interestRate)
                        .divide(100.toBigDecimal())
                        .divide(12.toBigDecimal(), 10, RoundingMode.DOWN)).toDouble()
        }

        /**
         * 現在の残高と年利から今月の支払い利息を計算
         */
        fun calcMonthInterest(balance: Long, interestRate: Double): Long {
            return (balance.toBigDecimal()
                        .multiply(BigDecimal.valueOf(mouthRate(interestRate)))
                        .setScale( 0, RoundingMode.DOWN))
                        .toLong()
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
            val numerator = (balance.toBigDecimal()
                                .multiply(BigDecimal.valueOf(mouthRate))
                                .multiply((
                                    BigDecimal.valueOf(1 + mouthRate)).pow(repaymentMouthCount)))
                                .setScale(10, RoundingMode.DOWN)

            // 計算分母
            val denominator = (BigDecimal.valueOf(1 + mouthRate)
                                    .pow(repaymentMouthCount))
                                    .minus(1.toBigDecimal())
                                    .setScale(10, RoundingMode.DOWN)

            return  (numerator.divide(denominator, 0, RoundingMode.DOWN)).toLong()
        }
    }
}


